package org.cinnamon.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * created at 2017. 1. 6.
 * @author shin dongseong
 *
 */
@Controller
public class InitWizardController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/initWizard/**", method=RequestMethod.GET)
	String main() {
		logger.info("start");
		
		return "/initWizard";
	}
}
