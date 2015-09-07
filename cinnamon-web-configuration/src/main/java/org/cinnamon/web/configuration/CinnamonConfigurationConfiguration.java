package org.cinnamon.web.configuration;

import org.cinnamon.web.configuration.domain.EmailServer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 *
 * created date: 2015. 9. 7.
 * @author 신동성
 */
@Configuration
@EntityScan(basePackageClasses=EmailServer.class)
@EnableJpaRepositories
public class CinnamonConfigurationConfiguration {

}
