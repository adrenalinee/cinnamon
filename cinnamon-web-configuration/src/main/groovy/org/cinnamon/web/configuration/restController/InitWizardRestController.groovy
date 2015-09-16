package org.cinnamon.web.configuration.restController

import org.cinnamon.core.wizard.Wizard
import org.cinnamon.web.configuration.wizard.InitWizard;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@RestController
@RequestMapping("/rest/configuration/initWizard")
class InitWizardRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	InitWizard initWizard
	
	
	@RequestMapping("")
	Wizard wizard() {
		logger.info("start")
		
		initWizard.createInitWizard()
	}
}
