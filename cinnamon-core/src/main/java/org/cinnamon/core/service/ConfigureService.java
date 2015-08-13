package org.cinnamon.core.service;

import org.cinnamon.core.init.ConfigureManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * create date: 2015. 5. 26.
 * @author 신동성
 *
 */
@Service
public class ConfigureService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ConfigureManager configureManager;
	
	@Transactional
	public void configure() {
		logger.info("start");
		
		configureManager.configure();
	}
}
