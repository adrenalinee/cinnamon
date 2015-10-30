package org.cinnamon.core;

import org.cinnamon.core.domain.UserBase;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
	
	
	@Bean
	Mapper beanMapper() {
		return new DozerBeanMapper();
	}
}
