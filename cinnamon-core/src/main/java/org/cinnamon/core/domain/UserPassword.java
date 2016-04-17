package org.cinnamon.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * create date: 2015. 4. 29.
 * @author sds
 *
 */
@Entity
public class UserPassword implements Serializable {
	
	private static final long serialVersionUID = -7186576450795308956L;

	@Id
	String userId;
	
	/**
	 * 암호화된 비밀번호
	 * 암호화 되기 전에 30자 이하
	 */
	@Column(nullable=false, length=500)
	String password;
	
	/**
	 * 비밀번호 초기화 이메일 발송시 생성되는 키.
	 * 이 키가 맞아야 비밀번호를 바꿀 수 있다.
	 * 부정하게 비밀번호가 바뀌는 것을 막기 위해 사용함.
	 */
	@Column(length=30)
	String changeKey;
	
	@Column(updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChangeKey() {
		return changeKey;
	}

	public void setChangeKey(String changeKey) {
		this.changeKey = changeKey;
	}
	
}
