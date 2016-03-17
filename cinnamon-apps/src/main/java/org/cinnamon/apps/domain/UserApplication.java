package org.cinnamon.apps.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.UserBase;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * create date: 2015. 6. 16.
 * @author 신동성
 *
 */
@Entity
public class UserApplication {
	
	@EmbeddedId
	UserApplicationId id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	UserBase userBase;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="app_id", insertable=false, updatable=false)
	Application application;
	
	/**
	 * 등록일
	 */
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
	
	public UserApplicationId getId() {
		return id;
	}

	public void setId(UserApplicationId id) {
		this.id = id;
	}

	public UserBase getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBase userBase) {
		this.userBase = userBase;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * 
	 * create date: 2015. 6. 16.
	 * @author 신동성
	 *
	 */
	@Embeddable
	@SuppressWarnings("serial")
	public static class UserApplicationId implements Serializable {
		
		@Column(name="user_id")
		String userId;
		
		@Column(name="app_id")
		Long appId;
		
		public UserApplicationId() {
			
		}
		
		public UserApplicationId(String userId, Long appId) {
			this.userId = userId;
			this.appId = appId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Long getAppId() {
			return appId;
		}

		public void setAppId(Long appId) {
			this.appId = appId;
		}
		
	}

}
