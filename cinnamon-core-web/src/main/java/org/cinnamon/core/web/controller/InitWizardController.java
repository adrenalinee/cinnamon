package org.cinnamon.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * created at 2017. 1. 6.
 * @author shin dongseong
 *
 */
public class InitWizardController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/initWizard/**")
	String main() {
		logger.info("start");
		
		return "/core/initWizard";
	}
}
