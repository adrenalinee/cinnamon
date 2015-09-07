package org.cinnamon.web;

import javax.annotation.PostConstruct;

import org.cinnamon.core.CinnamonCoreConfiguration;
import org.cinnamon.core.init.ConfigureManager;
import org.cinnamon.core.service.ConfigureService;
import org.cinnamon.web.configuration.CinnamonConfigurationConfiguration;
import org.cinnamon.web.init.ConsoleSiteConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * 
 * @author sds
 *
 */
@SpringBootApplication
@Import(value={
		CinnamonCoreConfiguration.class,
		CinnamonConfigurationConfiguration.class})
public class Application extends SpringBootServletInitializer {
	
	@Autowired
	ConfigureManager configureManager;
	
	@Autowired
	ConfigureService configureService;
	
	
	@PostConstruct
	void postConstruct() {
		configureManager.add(new ConsoleSiteConfigurer());
		
		configureService.configure();
	}
	
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
