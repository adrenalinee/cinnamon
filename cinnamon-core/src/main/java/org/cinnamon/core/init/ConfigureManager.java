package org.cinnamon.core.init;

import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.init.builder.SiteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * create date: 2015. 5. 26.
 * @author 신동성
 *
 */
@Component
public class ConfigureManager {
	
	List<SiteConfigurer> siteConfigureres = new LinkedList<SiteConfigurer>();
	
	@Autowired
	SiteBuilder siteBuilder;
	
//	@PostConstruct
//	public void postConstruct() {
//		siteConfigureres.add(new ConsoleSiteConfigurer());
//	}
	
	
	public void add(SiteConfigurer configurere) {
		siteConfigureres.add(configurere);
	}
	
//	public Stream<SiteConfigurere> stream() {
//		return siteConfigureres.stream();
//	}
	
	public void configure() {
//		SiteBuilder siteBuilder = new SiteBuilder();
		
		
		siteConfigureres.forEach(configure -> {
			configure.configure(siteBuilder);
		});
		
		
		siteBuilder.build();
	}
}
