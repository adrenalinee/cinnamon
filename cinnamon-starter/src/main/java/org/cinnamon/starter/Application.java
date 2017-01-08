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
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
