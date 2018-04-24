package org.cinnamon.core.rest.restcontroller;

import org.cinnamon.core.CinnamonCoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 
 * @author malibu
 *
 */
@Import(CinnamonCoreConfiguration.class)
@ComponentScan
@Configuration
public class CinnamonCoreRestConfiguration {

}
