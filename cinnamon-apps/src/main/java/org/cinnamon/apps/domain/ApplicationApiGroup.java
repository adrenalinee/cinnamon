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
public class ApplicationApiGroup {
	
	@Id
	@GeneratedValue
	Long clientApiGroupId;
	
	@ManyToOne
	Application application;
	
	@ManyToOne
	ApiGroup apiGroup;

	public Long getClientApiGroupId() {
		return clientApiGroupId;
	}

	public void setClientApiGroupId(Long clientApiGroupId) {
		this.clientApiGroupId = clientApiGroupId;
	}

	public ApiGroup getApiGroup() {
		return apiGroup;
	}

	public void setApiGroup(ApiGroup apiGroup) {
		this.apiGroup = apiGroup;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	
}
