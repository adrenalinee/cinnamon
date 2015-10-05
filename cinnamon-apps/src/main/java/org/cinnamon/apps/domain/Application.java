package org.cinnamon.apps.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * create date: 2015. 5. 14.
 * @author 신동성
 *
 */
@Entity
public class Application {
	
	@Id
	@GeneratedValue
	Long appId;
	
	@Column(nullable=false, length=100)
	String name;
	
	@Column(length=4000)
	String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="application")
	List<Client> clients;
	
	
	@ManyToOne
	ApplicationRole role;
	
	
//	@JsonIgnore
//	@OneToMany(mappedBy="application")
//	List<ApplicationApiGroup> applicationApiGroups;
	
//	@ManyToOne(optional=false)
//	Company company;
	
	
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

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

//	public List<ApplicationApiGroup> getApplicationApiGroups() {
//		return applicationApiGroups;
//	}
//
//	public void setApplicationApiGroups(List<ApplicationApiGroup> applicationApiGroups) {
//		this.applicationApiGroups = applicationApiGroups;
//	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public ApplicationRole getRole() {
		return role;
	}

	public void setRole(ApplicationRole role) {
		this.role = role;
	}

//	public Company getCompany() {
//		return company;
//	}
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}
	
	
	
}
