package org.cinnamon.web.configuration

import org.cinnamon.web.configuration.interceptor.InitCheckInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@Configuration
class CinnamonCoreWebConfiguration {

	@Configuration
	static class CinnamonWebConfiguration extends WebMvcConfigurerAdapter {
		@Autowired
		InitCheckInterceptor initCheckInterceptor

		@Override
		void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(initCheckInterceptor)
					.addPathPatterns("/**")
					.excludePathPatterns(
					"/configuration/partials/**",
					"/configuration/initWizard",
					"/rest/**",
					"/error/**")
		}
	}

	@Configuration
	static class CinnamonWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.anyRequest().authenticated()
//			http.formLogin()
			//			http.httpBasic()

//			http.authorizeRequests()
//					.antMatchers("/rest/**").anonymous()

			//			http.antMatcher("/rest/**").authorizeRequests().anyRequest().authenticated()
			//			http.antMatcher("/rest/**").httpBasic()


//			http.authorizeRequests().antMatchers("/rest/**")

		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring()
					.antMatchers(
					"/",
					"/webjars/**",
					"/fonts/**",
					"/configuration/partials/**",
					"/configuration/initWizard");
		}
	}
}
