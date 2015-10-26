package org.cinnamon.core.config;

import java.util.Map;

import javax.persistence.EntityManager;

import org.cinnamon.core.config.builder.BaseDataBuilder;
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
public class SystemInitData implements InitData {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ApplicationContext ac;
	
	
	@Override
	public void save(EntityManager em) throws Exception {
		logger.info("start");
		
		BaseDataBuilder baseDataBuilder = new BaseDataBuilder(em);
		
		Map<String, SystemConfigurer> systemConfigurers = ac.getBeansOfType(SystemConfigurer.class);
		systemConfigurers.forEach((name, systemConfigurer) -> {
			systemConfigurer.configure(baseDataBuilder);
		});
		
		baseDataBuilder.print();
		baseDataBuilder.build();
	}

	@Override
	public int order() {
		return -100;
	}
}
