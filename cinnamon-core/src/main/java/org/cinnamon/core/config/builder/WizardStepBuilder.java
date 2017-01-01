package org.cinnamon.core.config.builder;

import org.cinnamon.core.wizard.WizardStep;

/**
 * 
 * created at: 2016. 10. 4.
 * @author shindongseong
 */
public class WizardStepBuilder {
	private WizardBuilder parent;
	
	private WizardStep wizardStep;
	
	WizardStepBuilder(WizardBuilder parent) {
		this.parent = parent;
		wizardStep = new WizardStep();
	}
	
	public WizardStepBuilder name(String name) {
		wizardStep.setName(name);
		return this;
	}
	
	public WizardStepBuilder uri(String uri) {
		wizardStep.setUri(uri);
		return this;
	}
	
	public WizardStepBuilder description(String description) {
		wizardStep.setDescription(description);
		return this;
	}
	
	public WizardBuilder and() {
		return parent;
	}
	
	public WizardStep build() {
		
		
		return wizardStep;
	}
}