package org.cinnamon.apps.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	String name;
	
	String description;
	
	@OneToMany(mappedBy="apiGroup")
	List<ApplicationApiGroup> clientApiGroup;

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

	public List<ApplicationApiGroup> getClientApiGroup() {
		return clientApiGroup;
	}

	public void setClientApiGroup(List<ApplicationApiGroup> clientApiGroup) {
		this.clientApiGroup = clientApiGroup;
	}
	
	
}
