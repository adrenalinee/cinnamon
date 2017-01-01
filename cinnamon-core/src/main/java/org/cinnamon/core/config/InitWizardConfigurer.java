package org.cinnamon.core.config;

import org.cinnamon.core.config.builder.WizardBuilder;

/**
 * 
 * @author shin dongseong
 * @since 2016. 12. 21.
 */
public interface InitWizardConfigurer {
	void configure(WizardBuilder wizardBuilder);
}
