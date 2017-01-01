package org.cinnamon.core.wizard;

/**
 * 
 * created at: 2016. 9. 27.
 * @author shindongseong
 */
public class WizardStep {
	
	private int order;
	
	private String name;
	
	private String uri;
	
	private String description;

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
