package org.cinnamon.core.initWizard;

import java.util.List;

/**
 * 
 * created at: 2016. 9. 27.
 * @author shindongseong
 */
public class Wizard {
	private String name;
	
	private String title;
	
	private String description;
	
	/**
	 * 마지막단계가 성공한후 표시할 메시지
	 */
	private String finishedMessage;
	
	/**
	 * 마법사 끝까지 정 상진행한 후에 이동할 경로
	 */
	private String postFinishedUri;
	
	/**
	 * 진행중인 단계
	 */
	private int currentStep;
	
	private boolean completed;
	
	private List<WizardStep> steps;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<WizardStep> getSteps() {
		return steps;
	}

	public void setSteps(List<WizardStep> steps) {
		this.steps = steps;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getPostFinishedUri() {
		return postFinishedUri;
	}

	public void setPostFinishedUri(String postFinishedUri) {
		this.postFinishedUri = postFinishedUri;
	}

	public String getFinishedMessage() {
		return finishedMessage;
	}

	public void setFinishedMessage(String finishedMessage) {
		this.finishedMessage = finishedMessage;
	}
}