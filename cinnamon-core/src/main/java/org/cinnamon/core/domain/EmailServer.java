package org.cinnamon.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.enumeration.UseStatus;

/**
 * 
 * @author 동성
 * @since 2014. 12. 19.
 */
@SuppressWarnings("serial")
@Entity
public class EmailServer implements Serializable {
	
	@Id
	@GeneratedValue
	Long emailServerId;
	
	@Column(nullable=false, length=200)
	String name;
	
	@Column(nullable=false, length=200)
	String address;
	
	Integer port;
	
	@Column(length=200)
	String username;
	
	/**
	 * 암호화 예정
	 */
	@Column(length=500)
	String password;
	
	/**
	 * 보내는 사람 주소
	 */
	@Column(length=200)
	String fromAddress;
	
	/**
	 * 보내는 사람 이름
	 */
	@Column(length=200)
	String fromName;
	
	/**
	 * 보내는 메일 접두사
	 */
	@Column(length=200)
	String subjectPrefix;
	
	
	/**
	 * 기본 메일 서버 여부
	 */
	@Column(nullable=false)
	boolean defaultServer = false;
	
	
	@Column(length=4000)
	String description;
	
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

	public Long getEmailServerId() {
		return emailServerId;
	}

	public void setEmailServerId(Long emailServerId) {
		this.emailServerId = emailServerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public boolean isDefaultServer() {
		return defaultServer;
	}

	public void setDefaultServer(boolean defaultServer) {
		this.defaultServer = defaultServer;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getSubjectPrefix() {
		return subjectPrefix;
	}

	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
}
