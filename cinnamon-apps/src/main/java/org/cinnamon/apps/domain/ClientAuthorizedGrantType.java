package org.cinnamon.apps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.cinnamon.apps.domain.enumeration.AuthorizationGrantType;

/**
 * 
 *
 * created date: 2015. 10. 20.
 * @author 신동성
 */
@Entity
public class ClientAuthorizedGrantType {
	
	@Id
	@GeneratedValue
	Long clientAuthorizedGrantTypeId;
	
	@Column(nullable=false, length=50)
	@Enumerated(EnumType.STRING)
	AuthorizationGrantType grantType;
	
	@ManyToOne
	Client client;
	
	public Long getClientAuthorizedGrantTypeId() {
		return clientAuthorizedGrantTypeId;
	}

	public void setClientAuthorizedGrantTypeId(Long clientAuthorizedGrantTypeId) {
		this.clientAuthorizedGrantTypeId = clientAuthorizedGrantTypeId;
	}

	public AuthorizationGrantType getGrantType() {
		return grantType;
	}

	public void setGrantType(AuthorizationGrantType grantType) {
		this.grantType = grantType;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
