package org.cinnamon.apps.domain;

import javax.persistence.Column;
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
public class ApiGroup {
	
	@Id
	@GeneratedValue
	Long apiGroupId;
	
	@Column(length=100, nullable=false)
	String name;
	
	
	@ManyToOne
	Resource resource;
	
	@Column(length=4000)
	String description;
	
	public Long getApiGroupId() {
		return apiGroupId;
	}

	public void setApiGroupId(Long apiGroupId) {
		this.apiGroupId = apiGroupId;
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

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
}
