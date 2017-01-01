package org.cinnamon.apps;

import org.cinnamon.apps.domain.ApiDefinition;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 *
 * created date: 2015. 9. 30.
 * @author 신동성
 */
//@EntityScan(basePackageClasses=ApiDefinition.class)
@ComponentScan
@Configuration
@EnableJpaRepositories
public class CinnamonAppsConfiguration {

}
