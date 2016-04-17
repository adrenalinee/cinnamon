package org.cinnamon.web.configuration

import java.text.DateFormat
import java.text.SimpleDateFormat

import javax.sql.DataSource

import org.cinnamon.core.security.DatabasePermissionVoter
import org.cinnamon.core.security.UserDetailServiceImpl
import org.cinnamon.web.configuration.interceptor.InitCheckInterceptor
import org.cinnamon.web.configuration.rememberme.PersistentLogins
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@EntityScan(basePackageClasses=PersistentLogins.class)
@ComponentScan
@Configuration
class CinnamonCoreWebConfiguration {
	
	@Configuration
//	@EnableWebMvc
	protected static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
		
		@Override
		public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
			DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
			defaultDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"))
			
			for (HttpMessageConverter<?> conterter: converters) {
				if (conterter instanceof AbstractJackson2HttpMessageConverter) {
					AbstractJackson2HttpMessageConverter jacksonConverter =
							(AbstractJackson2HttpMessageConverter) conterter
					ObjectMapper om = jacksonConverter.getObjectMapper()
					om.setSerializationInclusion(Include.NON_NULL)
					om.setDateFormat(defaultDateFormat);
					om.registerModule(new Hibernate4Module())
				}
			}
		}
	}
	
	
	@Configuration
	protected static class CinnamonWebWebMvcConfigurer extends WebMvcConfigurerAdapter {
		
		@Autowired
		InitCheckInterceptor initCheckInterceptor
		
		@Override
		void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(initCheckInterceptor)
				.addPathPatterns(
					"/**",
					"/rest/configuration/initWizard/**")
				.excludePathPatterns(
					"/template/**",
					"/configuration/partials/**",
					"/configuration/initWizard",
					"/rest/**",
					"/error/**")
		}
	}
	
	
	@Order(10)
	@Configuration
	protected static class CinnamonWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
		
		@Autowired
		UserDetailServiceImpl userDetailService
		
		@Autowired
		DatabasePermissionVoter databaseRoleVoter
		
		@Autowired
		DataSource datasource
		
		
		@Bean
		PersistentTokenRepository persistentTokenRepository() {
			JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl()
			jdbcTokenRepositoryImpl.setDataSource(datasource)
			return jdbcTokenRepositoryImpl
		}
		
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.userDetailsService(userDetailService)
					.passwordEncoder(new BCryptPasswordEncoder())
		}
		
		
		@Override
		void configure(HttpSecurity http) throws Exception {
//			http.antMatcher("/rest/**").authorizeRequests().anyRequest().authenticated()
			
			http.authorizeRequests().antMatchers("/join/**", "/findPassword/**").anonymous()
			http.authorizeRequests().antMatchers("/rest/accounts/**").anonymous()
			http.authorizeRequests().antMatchers("/rest/**").authenticated()
//			http.authorizeRequests().regexMatchers(HttpMethod.HEAD, "/rest/accounts/*")
//			http.authorizeRequests().regexMatchers(HttpMethod.POST, "/rest/accounts/*")
			
			
			http.antMatcher("/**").authorizeRequests().anyRequest().denyAll()
			http.formLogin().loginPage("/login").permitAll()

			// 이메일 인증 관련 추가
			http.authorizeRequests().antMatchers("/platform/users/**/email/confirm").permitAll()

			http.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				
//			http.rememberMe().tokenRepository(new InMemoryTokenRepositoryImpl())
			http.rememberMe().tokenRepository(persistentTokenRepository())
			
			WebExpressionVoter webExpressionVoter = new WebExpressionVoter()
			AffirmativeBased accessDecisionManager = new AffirmativeBased(Arrays.asList(databaseRoleVoter, webExpressionVoter))
			
			http.authorizeRequests().accessDecisionManager(accessDecisionManager)
		}
		
		
		@Override
		void configure(WebSecurity web) throws Exception {
			web.ignoring()
					.antMatchers(
					"/",
//					"/join",
					"/webjars/**",
					"/fonts/**",
					"/configuration/partials/**",
					"/settings/partials/**",
					"/configuration/initWizard/**",
					"/rest/configuration/initWizard/**");
		}
	}
}
