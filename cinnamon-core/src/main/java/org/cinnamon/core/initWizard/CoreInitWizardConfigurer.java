package org.cinnamon.core.initWizard;

import org.cinnamon.core.config.InitWizardConfigurer;
import org.cinnamon.core.config.builder.WizardBuilder;
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
			.uri("wellcome").and()
		.step("database")
			.uri("database").and()
		.step("baseData")
			.uri("baseData");
	}

}
