package org.cinnamon.core.initWizard.builder;

import java.util.ArrayList;
import java.util.List;

import org.cinnamon.core.initWizard.Wizard;
import org.cinnamon.core.initWizard.WizardStep;

import com.google.common.collect.Lists;

/**
 * 
 * created at: 2016. 10. 4.
 * @author shindongseong
 */
public class WizardBuilder {
	private Wizard wizard;
	
	private List<WizardStepBuilder> wizardStepBuilders = Lists.newArrayList();
	
	public WizardBuilder() {
		wizard = new Wizard();
	}
	
	public WizardStepBuilder step(String name) {
		WizardStepBuilder wizardStepBuilder = new WizardStepBuilder(this);
		wizardStepBuilders.add(wizardStepBuilder);
		return wizardStepBuilder.name(name);
	}
	
	public WizardBuilder name(String name) {
		wizard.setName(name);
		return this;
	}
	
	public WizardBuilder description(String description) {
		wizard.setDescription(description);
		return this;
	}
	
	public WizardBuilder finishedMessage(String finishedMessage) {
		wizard.setFinishedMessage(finishedMessage);
		return this;
	}
	
	public WizardBuilder postFinishedUri(String postFinishedUri) {
		wizard.setPostFinishedUri(postFinishedUri);
		return this;
	}
	
	public Wizard build() {
		List<WizardStep> wizardSteps = new ArrayList<>();
		wizardStepBuilders.forEach(wsb -> wizardSteps.add(wsb.build()));
		wizard.setSteps(wizardSteps);
		
		return wizard;
	}
}