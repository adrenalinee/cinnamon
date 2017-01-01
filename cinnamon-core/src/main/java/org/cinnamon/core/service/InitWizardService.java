package org.cinnamon.core.service;

import javax.persistence.EntityManager;

import org.cinnamon.core.config.InitWizardConfigurer;
import org.cinnamon.core.config.builder.WizardBuilder;
import org.cinnamon.core.wizard.Wizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 
 * created at: 2016. 10. 4.
 * @author shindongseong
 */
@Service
public class InitWizardService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private EntityManager em;
	
	private Wizard initWizard;
	
	/**
	 * 
	 * @return
	 */
	public Wizard getInitWizard() {
		logger.info("start");
		if (initWizard == null) {
			WizardBuilder wizardBuilder = new WizardBuilder();
			ac.getBeansOfType(InitWizardConfigurer.class)
				.values()
				.forEach(configurer -> configurer.configure(wizardBuilder));
			
			initWizard = wizardBuilder.build();
		}
		
		return initWizard;
	}
	
}