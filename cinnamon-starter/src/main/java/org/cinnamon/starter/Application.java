package org.cinnamon.starter;

import org.cinnamon.core.web.CinnamonCoreWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 
 * @author shindongseong
 * @since 2016. 12. 20.
 */
@SpringBootApplication
@Import(value={
		CinnamonCoreWebConfiguration.class})
public class Application /*extends SpringBootServletInitializer*/ {
	
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
	
//	@ControllerAdvice(annotations=RestController.class)
//	public static class CoreStarterRestControllerAdvice extends CommonRestControllerAdvice {
//		
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}
}
