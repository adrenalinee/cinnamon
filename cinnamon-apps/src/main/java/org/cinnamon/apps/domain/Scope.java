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
public class Scope implements Serializable {
	
	@Id
	@Column(length=20)
	String scope;
	
	@Column(length=4000)
	String description;
	
	public Scope() {
		
	}
	
	public Scope(String scope) {
		this.scope = scope;
	}
	
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
