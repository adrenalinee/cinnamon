package org.cinnamon.web;

import org.cinnamon.apps.CinnamonAppsConfiguration;
import org.cinnamon.core.CinnamonCoreConfiguration;
import org.cinnamon.core.advice.CommonRestControllerAdvice;
import org.cinnamon.web.configuration.CinnamonCoreWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author sds
 *
 */
@SpringBootApplication
@Import(value={
		CinnamonCoreConfiguration.class,
		CinnamonAppsConfiguration.class,
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
	
//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//		registration.addUrlMappings("/h2console/*");
//		return registration;
//	}
	
	@ControllerAdvice(annotations=RestController.class)
	public static class CoreStarterRestControllerAdvice extends CommonRestControllerAdvice {
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
