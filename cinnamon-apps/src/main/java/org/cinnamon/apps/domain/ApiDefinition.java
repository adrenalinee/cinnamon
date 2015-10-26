package org.cinnamon.apps.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@Column(length=200)
	String url;
	
	@Column(length=100)
	String name;
	
	@Column(length=4000)
	String description;
	
	@ManyToOne
	ApiGroup apiGroup;
	
	@ManyToMany
	List<ApplicationAuthority> authorities;
	
//	@OneToMany
//	List<ApiScope> permittedScope;
	
	@ManyToMany
	List<Scope> permittedScopes;
	
	
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

	public List<ApplicationAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<ApplicationAuthority> authorities) {
		this.authorities = authorities;
	}

//	public List<ApiScope> getPermittedScope() {
//		return permittedScope;
//	}
//
//	public void setPermittedScope(List<ApiScope> permittedScope) {
//		this.permittedScope = permittedScope;
//	}

	public List<Scope> getPermittedScopes() {
		return permittedScopes;
	}

	public void setPermittedScopes(List<Scope> permittedScopes) {
		this.permittedScopes = permittedScopes;
	}

}
