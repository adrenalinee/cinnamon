package org.cinnamon.core.web.restController;

import org.cinnamon.core.initWizard.InitWizardService;
import org.cinnamon.core.initWizard.Wizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * created at: 2017. 1. 26.
 * @author dsshin
 */
//@RestController
//@RequestMapping("/rest/configuration/initWizard")
public class InitWizardRestController2 {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private InitWizardService initWizardService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	ResponseEntity<Wizard> getInitWizard() {
		logger.info("start");
		return ResponseEntity.ok(initWizardService.getInitWizard());
	}
	
}
