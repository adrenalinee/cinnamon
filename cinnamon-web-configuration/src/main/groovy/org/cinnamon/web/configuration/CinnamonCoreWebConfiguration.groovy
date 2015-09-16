package org.cinnamon.web.configuration

import org.cinnamon.web.configuration.interceptor.InitCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
					"/configuration/initWizard/**",
					"/rest/configuration/initWizard/**",
					"/error/**")
		}
	}
}
