package org.cinnamon.core.web;

import org.cinnamon.core.CinnamonCoreConfiguration;
import org.cinnamon.core.web.interceptor.InitCheckInterceptor;
import org.cinnamon.core.web.rememberme.PersistentLogins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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

@Configuration
class WebMvcConfiger extends WebMvcConfigurerAdapter {
	
	@Autowired
	private InitCheckInterceptor initCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(initCheckInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(
					"/core/components/**",
					"/initWizard/**",
					"/error/**");
	}
}

//@Configuration
//class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//	
//}