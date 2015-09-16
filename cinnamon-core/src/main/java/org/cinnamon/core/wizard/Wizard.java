package org.cinnamon.core.wizard;

import java.util.List;

/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
public class Wizard {
	
	private String name;
	
	private String description;
	
	private List<WizardStep> wizardSteps;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WizardStep> getWizardSteps() {
		return wizardSteps;
	}

	public void setWizardSteps(List<WizardStep> wizardSteps) {
		this.wizardSteps = wizardSteps;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
