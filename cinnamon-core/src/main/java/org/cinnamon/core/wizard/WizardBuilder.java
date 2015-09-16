package org.cinnamon.core.wizard;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
public class WizardBuilder {
	
	private List<WizardStep> wizardSteps = new LinkedList<WizardStep>();
	
	private int stepCount;
	
	private String name;
	
	private String description;
	
	
	public WizardBuilder setName(String name) {
		this.name = name;
		
		return this;
	}
	
	
	public WizardBuilder setDescription(String description) {
		this.description = description;
		
		return this;
	}
	
	
	public WizardBuilder addStep(WizardStep wizardStep) {
		wizardSteps.add(wizardStep);
		
		return this;
	}
	
	
	public WizardBuilder addStep(String name, String uri, String description) {
		WizardStep wizardStep = new WizardStep();
		wizardStep.setOrder(stepCount++);
		wizardStep.setName(name);
		wizardStep.setUri(uri);
		wizardStep.setDescription(description);
		
		wizardSteps.add(wizardStep);
		
		return this;
	}
	
	
	public Wizard build() {
		Wizard wizard = new Wizard();
		wizard.setWizardSteps(wizardSteps);
		wizard.setName(name);
		wizard.setDescription(description);
		
		clear();
		
		return wizard;
	}


	private void clear() {
		name = null;
		description = null;
		wizardSteps = new LinkedList<WizardStep>();
		stepCount = 0;
	}
}
