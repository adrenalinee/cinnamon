package org.cinnamon.web.configuration

import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.web.configuration.interceptor.InitCheckInterceptor
import org.cinnamon.web.configuration.security.DatabasePermissionVoter
import org.cinnamon.web.configuration.security.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@ComponentScan
@Configuration
class CinnamonCoreWebConfiguration {

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
					"/configuration/partials/**",
					"/configuration/initWizard",
					"/rest/**",
					"/error/**")
		}
	}
	
	@Configuration
	protected static class CinnamonWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
		
		@Autowired
		UserDetailServiceImpl userDetailService
		
		@Autowired
		DatabasePermissionVoter databaseRoleVoter
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.userDetailsService(userDetailService)
					.passwordEncoder(new BCryptPasswordEncoder());
		}
		
//		@Override
//		void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth
//				.userDetailsService(userDetailService)
//					.passwordEncoder(new BCryptPasswordEncoder())
//		}
		
		@Override
		void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/rest/**").authorizeRequests().anyRequest().authenticated()
			
			
			http.antMatcher("/**").authorizeRequests().anyRequest().denyAll()
			http.formLogin().loginPage("/login").permitAll()
			
			
			WebExpressionVoter webExpressionVoter = new WebExpressionVoter()
			AffirmativeBased accessDecisionManager = new AffirmativeBased(Arrays.asList(databaseRoleVoter, webExpressionVoter))
			
			http.authorizeRequests().accessDecisionManager(accessDecisionManager)
		}

		@Override
		void configure(WebSecurity web) throws Exception {
			web.ignoring()
					.antMatchers(
					"/",
					"/webjars/**",
					"/fonts/**",
					"/configuration/partials/**",
					"/configuration/initWizard/**",
					"/rest/configuration/initWizard/**");
		}
	}
}
