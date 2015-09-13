package org.cinnamon.core;

import org.cinnamon.core.domain.UserBase;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * 
 * @author sds
 *
 */
@Configuration
@ComponentScan
@EntityScan(basePackageClasses=UserBase.class)
@EnableJpaRepositories
public class CinnamonCoreConfiguration {
	
	
	@Configuration
	public static class CustomRepositoryRestMvcConfiguration extends RepositoryRestMvcConfiguration {
		
		@Override
		public RepositoryRestConfiguration config() {
			RepositoryRestConfiguration config = super.config();
			config.setBaseUri("/rest");
			
			return config;
		}
	}
}
