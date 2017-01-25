package org.cinnamon.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@Controller
@RequestMapping("/configuration/initWizard")
public class InitWizardController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("**")
	String main() {
		logger.info("start");
//		return "configuration/initWizard";
		return "configuration/initWizard2";
	}
}
