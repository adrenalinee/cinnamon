package org.cinnamon.core.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.enumeration.EntityType;
import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 
 * @author 동성
 *
 */
@Entity(name="user")
@Inheritance
public class UserBase implements UserEntity {
	
	@Id
	@Column(length=20)
	String userId;
	
//	@ManyToOne
//	AdministratorGroup administratorGroup;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER) //TODO 인증할 때 필요해서 이렇게 처리. 안해도 되는 방법 필요
	Set<UserGroup> userGroups = new HashSet<UserGroup>();
	
//	@JsonIgnore
//	@ManyToOne(optional=false)
//	UserGroup userGroup;
	
	@JsonIgnore
	@ManyToMany
	Set<Role> roles = new HashSet<Role>();
	
	@Column(length=100)
	String name;
	
//	String password;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	UserPassword userPassword;
	
	@Column(length=200)
	private String email;
	
	/**
	 * 이메일 인증되었는지 여부
	 */
	boolean validEmail;
	
//	@Enumerated(EnumType.STRING)
//	Gender gender;
//	
//	@Temporal(TemporalType.DATE)
//	Date birthday;
	
	/**
	 * 직급
	 */
//	@Column(length=100)
//	String position;
	
	/**
	 * 거주지
	 */
	@Column(length=200)
	String location;
	
	/**
	 * 직업
	 */
	String job;
	
	/**
	 * 연락처
	 */
	@Column(length=20)
	String tel;
	
	@Column(length=20)
	String phone;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	UserIntroduce userIntroduce;
	
	/**
	 * 가입 승인이 되었는지 여부
	 */
	Boolean accepted;
	
	/**
	 * 가입 승인일시
	 */
	@Temporal(TemporalType.TIMESTAMP)
	Date acceptedAt;
	
	/**
	 * 사용언어.
	 * 웹 표시언어에 영향을 미침
	 */
	@Column(length=10)
	String language;
	
	/**
	 * 국적
	 */
	@Column(length=10)
	String nation;
	
	/**
	 * 마지막 로그인 일시
	 */
	@Temporal(TemporalType.TIMESTAMP)
	Date lastLoginAt;
	
	@Column(length=50)
	@Enumerated(EnumType.STRING)
	EntityType entityType;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

//	public String getAdminId() {
//		return adminId;
//	}
//
//	public void setAdminId(String adminId) {
//		this.adminId = adminId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

//	public String getPosition() {
//		return position;
//	}
//
//	public void setPosition(String position) {
//		this.position = position;
//	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isValidEmail() {
		return validEmail;
	}

	public void setValidEmail(boolean validEmail) {
		this.validEmail = validEmail;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getAcceptedAt() {
		return acceptedAt;
	}

	public void setAcceptedAt(Date acceptedAt) {
		this.acceptedAt = acceptedAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

//	public Gender getGender() {
//		return gender;
//	}
//
//	public void setGender(Gender gender) {
//		this.gender = gender;
//	}

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public UserIntroduce getUserIntroduce() {
		return userIntroduce;
	}

	public void setUserIntroduce(UserIntroduce userIntroduce) {
		this.userIntroduce = userIntroduce;
	}

//	public Date getBirthday() {
//		return birthday;
//	}
//
//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public UserPassword getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
