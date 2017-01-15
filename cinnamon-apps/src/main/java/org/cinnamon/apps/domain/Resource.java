package org.cinnamon.apps.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 *
 * created date: 2015. 10. 19.
 * @author 신동성
 */
@Entity
public class Resource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1945177568965666742L;

	@Id
	@Column(length=100)
	String resourceId;
	
	@Column(length=100)
	String name;
	
	@Column(length=4000)
	String description;
	
	public Resource() {
		
	}
	
	public Resource(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
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
	
}
