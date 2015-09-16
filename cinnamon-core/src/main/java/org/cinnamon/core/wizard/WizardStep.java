package org.cinnamon.core.wizard;

/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
public class WizardStep {
	
	/**
	 * 순서
	 */
	int order;
	
	/**
	 * 이름
	 */
	String name;
	
	/**
	 * 경로
	 */
	String uri;
	
	/**
	 * 설명
	 */
	String description;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
