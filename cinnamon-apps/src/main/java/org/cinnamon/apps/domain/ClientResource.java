package org.cinnamon.apps.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 *
 * created date: 2015. 10. 20.
 * @author 신동성
 */
@Entity
public class ClientResource implements Serializable {
	
	@Id
	@GeneratedValue
	Long clientResourceId;
	
	String resourceId;
	
	@ManyToOne
	Client client;

	public Long getClientResourceId() {
		return clientResourceId;
	}

	public void setClientResourceId(Long clientResourceId) {
		this.clientResourceId = clientResourceId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
}
