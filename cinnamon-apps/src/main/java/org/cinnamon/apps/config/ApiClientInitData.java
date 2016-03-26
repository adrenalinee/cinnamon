package org.cinnamon.apps.config;

import java.util.Map;

import javax.persistence.EntityManager;

import org.cinnamon.apps.config.builder.ApiClientDomainBuilder;
import org.cinnamon.core.config.InitData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * created date: 2015. 10. 26.
 * @author 신동성
 */
@Component
public class ApiClientInitData implements InitData {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ApplicationContext ac;
	
	
	@Override
	public void save(EntityManager em) throws Exception {
		logger.info("start");
		
		ApiClientDomainBuilder builder = new ApiClientDomainBuilder(em);
		
		Map<String, ApiClientConfigurer> apiClientConfigurers = ac.getBeansOfType(ApiClientConfigurer.class);
		apiClientConfigurers.forEach((name, apiClientConfigurer) -> {
			apiClientConfigurer.configure(builder);
		});
		
		builder.build();
	}

	@Override
	public int order() {
		return -90;
	}

}
