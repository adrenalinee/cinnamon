package org.cinnamon.core.config;

import org.cinnamon.core.initWizard.InitWizardConfigurer;
import org.cinnamon.core.initWizard.builder.WizardBuilder;
import org.springframework.stereotype.Component;

/**
 * 
 * created at 2017. 1. 6.
 * @author shin dongseong
 *
 */
@Component
public class CoreInitWizardConfigurer implements InitWizardConfigurer {

	@Override
	public void configure(WizardBuilder wizardBuilder) {
		wizardBuilder
		.name("initWizard")
		.step("wellcome")
			.uri("/core/components/initWizard/welcome").and()
		.step("database")
			.uri("/core/components/initWizard/database").and()
		.step("baseData")
			.uri("/core/components/initWizard/baseData").and()
		.step("firstUser")
			.uri("/core/components/initWizard/firstUser")
		
		;
	}

}
