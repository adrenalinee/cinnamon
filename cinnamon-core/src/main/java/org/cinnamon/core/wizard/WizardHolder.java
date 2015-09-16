package org.cinnamon.core.wizard;

import org.springframework.stereotype.Component;

/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
@Component
public class WizardHolder {
	
	private Wizard wizard;
	
	private int nowStep;

	public Wizard getWizard() {
		return wizard;
	}

	public void setWizard(Wizard wizard) {
		this.wizard = wizard;
	}
	
	public int nowStep() {
		return nowStep;
	}
	
	public void nextStep() {
		nowStep++;
	}
	
}
