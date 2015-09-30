package org.cinnamon.apps.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.enumeration.UseStatus;

/**
 * 
 * create date: 2015. 5. 12.
 * @author 신동성
 *
 */
@Entity
public class Client {
	
	@Id
	@Column(length=100)
	String clientId;
	
	@Column(nullable=false, length=100)
	String name;
	
	@Column(length=200)
	String secret;
	
	String type;
	
	@ManyToOne(optional=false)
	Application application;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	@Column(updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	

}
