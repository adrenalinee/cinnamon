package org.cinnamon.apps.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@Column(length=200)
	String secret;
	
	@Column(length=4000)
	String description;
	
//	tring type;
	
	Integer accessTokenValiditySeconds = 60 * 60 * 2;	// 2 hour 7200 sec
	
	//Integer refreshTokenValiditySeconds = 60 * 60 * 24 * 30;	// 30 days 
	Integer refreshTokenValiditySeconds = 0;
	
	
	@ManyToOne(optional=false)
	Application application;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	List<Scope> permittedScopes;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	List<Resource> permittedResources;
	
//	@OneToMany(mappedBy="client", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
//	List<ClientResource> hasResourceIds;
	
	
	@OneToMany(mappedBy="client", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	List<ClientRedirectUri> redirectUris;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	List<ClientAuthorizedGrantType> authorizedGrantTypes;
	
	
	@Column(nullable=false, length=50)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	@Column(updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
	
	public synchronized void addAuthorizedGrantType(ClientAuthorizedGrantType authorizedGrantType) {
		if (authorizedGrantTypes == null) {
			authorizedGrantTypes = new LinkedList<>();
		}
		authorizedGrantTypes.add(authorizedGrantType);
	}
	
	public synchronized void addPermittedScope(Scope scope) {
		if (permittedScopes == null) {
			permittedScopes = new LinkedList<>();
		}
		permittedScopes.add(scope);
	}
	
	public synchronized void addPermittedResource(Resource resource) {
		if (permittedResources == null) {
			permittedResources = new LinkedList<>();
		}
		permittedResources.add(resource);
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

	public List<ClientRedirectUri> getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(List<ClientRedirectUri> redirectUris) {
		this.redirectUris = redirectUris;
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public List<ClientAuthorizedGrantType> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(List<ClientAuthorizedGrantType> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Scope> getPermittedScopes() {
		return permittedScopes;
	}

	public void setPermittedScopes(List<Scope> permittedScopes) {
		this.permittedScopes = permittedScopes;
	}

	public List<Resource> getPermittedResources() {
		return permittedResources;
	}

	public void setPermittedResources(List<Resource> permittedResources) {
		this.permittedResources = permittedResources;
	}

}
