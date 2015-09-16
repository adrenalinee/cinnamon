package org.cinnamon.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.enumeration.UseStatus;

/**
 * 
 * @author 동성
 * @since 2015. 1. 6.
 */
@Entity
public class Property {
	
	@Id
	@Column(length=100)
	String name;
	
	@Column(length=200)
	String value;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	@Column(updatable=false, nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;
	
	String description;

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

	public String getValue() {
		return value;
	}
	
	public Long getLongValue() {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean getBooleanValue() {
		try {
			return Boolean.parseBoolean(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
