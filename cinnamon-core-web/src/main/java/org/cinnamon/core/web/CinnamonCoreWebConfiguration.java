package org.cinnamon.core.web;

import org.cinnamon.core.CinnamonCoreConfiguration;
import org.cinnamon.core.web.rememberme.PersistentLogins;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

//@Configuration
//class WebMvcConfiger extends WebMvcConfigurerAdapter {
//	
//}
//
//@Configuration
//class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//	
//}