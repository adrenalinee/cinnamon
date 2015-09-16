package org.cinnamon.core.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 동성
 *
 */
@Entity
public class Role {
	
//	@Id
//	@GeneratedValue
//	Long permissionId;
	
	@Id
	String authority;
	
	@Column(nullable=false)
	String name;
	
	/**
	 * 권한 문자.. (spring-security 사용..)
	 */
//	@Column(unique=true)
//	String authority;
	
	@JsonIgnore
	@OneToMany(mappedBy="role")
	List<RoleMenu> roleMenus;
	
	@JsonIgnore
	@OneToOne
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

	public List<RoleMenu> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}

	public UserGroup getDefaultUserGroup() {
		return defaultUserGroup;
	}

	public void setDefaultUserGroup(UserGroup defaultUserGroup) {
		this.defaultUserGroup = defaultUserGroup;
	}
	
}
