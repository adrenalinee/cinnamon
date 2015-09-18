package org.cinnamon.web;

import org.cinnamon.core.CinnamonCoreConfiguration;
import org.cinnamon.web.configuration.CinnamonCoreWebConfiguration;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 
 * @author sds
 *
 */
@SpringBootApplication
@Import(value={
		CinnamonCoreConfiguration.class,
		CinnamonCoreWebConfiguration.class})
public class Application extends SpringBootServletInitializer {
	
//	@Autowired
//	ConfigureManager configureManager;
//	
//	@Autowired
//	ConfigureService configureService;
//	
//	
//	@PostConstruct
//	void postConstruct() {
//		configureManager.add(new ConsoleSiteConfigurer());
//		
//		configureService.configure();
//	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/h2console/*");
		return registration;
	}
	
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
