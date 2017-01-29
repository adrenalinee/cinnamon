package org.cinnamon.core.initWizard;

import org.cinnamon.core.config.InitWizardConfigurer;
import org.cinnamon.core.config.builder.WizardBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 
 * created at 2017. 1. 5.
 * 
 * @author shin dongseong
 *
 */
@Service
public class InitWizardService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationContext ac;

	private Wizard initWizard;

	public synchronized Wizard getInitWizard() {
		logger.info("start");

		if (initWizard == null) {
			WizardBuilder wizardBuilder = new WizardBuilder();
			ac.getBeansOfType(InitWizardConfigurer.class).values()
				.forEach(configurer -> configurer.configure(wizardBuilder));
			
			initWizard = wizardBuilder.build();
		}

		return initWizard;
	}
}
