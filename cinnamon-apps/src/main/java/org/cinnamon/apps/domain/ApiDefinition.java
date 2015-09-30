package org.cinnamon.apps.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * create date: 2015. 5. 12.
 * @author 신동성
 *
 */
@Entity
public class ApiDefinition {
	
	@Id
	@GeneratedValue
	Long apiId;
	
	String url;
	
	String name;
	
	String description;
	
	@ManyToOne
	ApiGroup apiGroup;

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public ApiGroup getApiGroup() {
		return apiGroup;
	}

	public void setApiGroup(ApiGroup apiGroup) {
		this.apiGroup = apiGroup;
	}
	
	
}
