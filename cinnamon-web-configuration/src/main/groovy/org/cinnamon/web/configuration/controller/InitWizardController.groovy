package org.cinnamon.web.configuration.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@Controller
@RequestMapping("/configuration/initWizard")
class InitWizardController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@RequestMapping("**")
	def users() {
		logger.info("start")
		"configuration/initWizard"
	}
}
