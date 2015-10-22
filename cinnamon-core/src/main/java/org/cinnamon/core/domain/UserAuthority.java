package org.cinnamon.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 동성
 *
 */
@Entity
@Table(name="permission") //regacy DB 때문에 테이블 이름 다르게 함
public class UserAuthority {
	
	@Id
	@GeneratedValue
	@Column(name="permission_id")
	Long authorityId;
	
	@Column(nullable=false, length=50, unique=true)
	String authority;
	
	@Column(nullable=false)
	String name;
	
	/**
	 * 권한 문자.. (spring-security 사용..)
	 */
//	@Column(unique=true)
//	String authority;
	
//	@JsonIgnore
//	@OneToMany(mappedBy="authority")
//	List<MenuAuthority> roleMenus;
	
	@JsonIgnore
	@OneToOne(optional=true)
	UserGroup defaultUserGroup;
	
	
	@Column(nullable=false, length=20)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	
	@Column(length=4000)
	String description;
	
	
//	public Long getPermissionId() {
//		return permissionId;
//	}
//
//	public void setPermissionId(Long permissionId) {
//		this.permissionId = permissionId;
//	}

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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

//	public List<MenuAuthority> getRoleMenus() {
//		return roleMenus;
//	}
//
//	public void setRoleMenus(List<MenuAuthority> roleMenus) {
//		this.roleMenus = roleMenus;
//	}

	public UserGroup getDefaultUserGroup() {
		return defaultUserGroup;
	}

	public void setDefaultUserGroup(UserGroup defaultUserGroup) {
		this.defaultUserGroup = defaultUserGroup;
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

}
