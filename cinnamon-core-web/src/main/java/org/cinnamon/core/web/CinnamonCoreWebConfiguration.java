package org.cinnamon.core.web;

import java.util.Arrays;

import javax.sql.DataSource;

import org.cinnamon.core.CinnamonCoreConfiguration;
import org.cinnamon.core.security.DatabasePermissionVoter;
import org.cinnamon.core.security.UserDetailServiceImpl;
import org.cinnamon.core.web.interceptor.InitCheckInterceptor;
import org.cinnamon.core.web.rememberme.PersistentLogins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author shindongseong
 * @since 2016. 12. 20.
 */
@Import(CinnamonCoreConfiguration.class)
@EntityScan(basePackageClasses=PersistentLogins.class)
@ComponentScan
@Configuration
public class CinnamonCoreWebConfiguration {
	
}

/**
 * 
 * @author shindongseong
 */
@Configuration
class WebMvcConfiger extends WebMvcConfigurerAdapter {
	
	@Autowired
	private InitCheckInterceptor initCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(initCheckInterceptor)
		.addPathPatterns(
			"/**",
			"/rest/configuration/initWizard/**")
			.excludePathPatterns(
			"/template/**",
			"/configuration/partials/**",
			"/configuration/initWizard",
			"/rest/**",
			"/error/**");
	}
}

/**
 * 
 * @author shindongseong
 */
@Order(10)
@Configuration
class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailServiceImpl userDetailService;

	@Autowired
	DatabasePermissionVoter databaseRoleVoter;

	@Autowired
	DataSource datasource;

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(datasource);
		return jdbcTokenRepositoryImpl;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/join/**", "/findPassword/**"/*, "/captcha/**"*/).anonymous();
		http.authorizeRequests().antMatchers("/rest/accounts/**").anonymous();
		http.authorizeRequests().antMatchers("/rest/**").authenticated();
		
		http.authorizeRequests().antMatchers("/").permitAll();


		http.antMatcher("/**").authorizeRequests().anyRequest().denyAll();
		http.formLogin()
			.loginPage("/login").permitAll();

		// 이메일 인증 관련 추가
		http.authorizeRequests()
				.antMatchers("/platform/users/**/email/confirm", "/rest/accounts/initPassword")
				.permitAll();

		http.logout()
				.logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/login");

		//			http.rememberMe().tokenRepository(new InMemoryTokenRepositoryImpl())
		http.rememberMe().tokenRepository(persistentTokenRepository());

		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		AffirmativeBased accessDecisionManager = new AffirmativeBased(Arrays.asList(databaseRoleVoter, webExpressionVoter));

		http.authorizeRequests().accessDecisionManager(accessDecisionManager);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers(
//									"/",
//									"/join",
				"/webjars/**",
				"/fonts/**",
				"/configuration/partials/**",
				"/configuration/directives/**",
				"/settings/partials/**",
				"/configuration/initWizard/**",
				"/rest/configuration/initWizard/**"
//				"/rest/captcha/check"
				);
	}
}