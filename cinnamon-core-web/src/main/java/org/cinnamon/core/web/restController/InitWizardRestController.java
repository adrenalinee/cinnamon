package org.cinnamon.core.web.restController;

import org.cinnamon.core.initWizard.InitWizardService;
import org.cinnamon.core.initWizard.Wizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * created at 2017. 1. 6.
 * @author shin dongseong
 *
 */
@RestController
public class InitWizardRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private InitWizardService initWizardService;
	
	@RequestMapping(value="/initWizard", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	Wizard getInitWizard() {
		logger.info("start");
		
		return initWizardService.getInitWizard();
	}
	
	
}
