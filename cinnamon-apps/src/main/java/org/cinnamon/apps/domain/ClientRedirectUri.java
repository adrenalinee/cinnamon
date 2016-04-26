package org.cinnamon.apps.domain;

import java.io.Serializable;

import javax.persistence.Column;
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
public class ClientRedirectUri implements Serializable {
	
	@Id
	@GeneratedValue
	Long clientRedirectUriId;
	
	@Column(length=200)
	String uri;
	
	@ManyToOne
	Client client;

	public Long getClientRedirectUriId() {
		return clientRedirectUriId;
	}

	public void setClientRedirectUriId(Long clientRedirectUriId) {
		this.clientRedirectUriId = clientRedirectUriId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
