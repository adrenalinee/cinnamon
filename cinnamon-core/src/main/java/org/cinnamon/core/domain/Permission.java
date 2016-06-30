package org.cinnamon.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 동성
 *
 */
//@SuppressWarnings("serial")
@Entity
public class Permission implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7873817100182300172L;

	@Id
	@GeneratedValue
	Long permissionId;
	
	@Column(nullable=false)
	String name;
	
	/**
	 * 권한 문자.. (spring-security 사용..)
	 */
	@Column(nullable=false, length=50, unique=true)
	String authority;

	@JsonIgnore
	@OneToOne(optional=true)
	UserGroup defaultUserGroup;
	
	
	@Column(nullable=false, length=20)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	
	@Column(length=4000)
	String description;
	
	// 2016.03.07 추가 메뉴 권한도 같이 생성
	@JsonIgnore
	@OneToMany(mappedBy="permission")
	List<PermissionMenu> permissionMenus;
	
	/**
	 * 기본 메뉴
	 */
	@ManyToOne //(fetch=FetchType.LAZY)
	Menu defaultMenu;
	
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

	public UserGroup getDefaultUserGroup() {
		return defaultUserGroup;
	}

	public void setDefaultUserGroup(UserGroup defaultUserGroup) {
		this.defaultUserGroup = defaultUserGroup;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public List<PermissionMenu> getPermissionMenus() {
		return permissionMenus;
	}

	public void setPermissionMenus(List<PermissionMenu> permissionMenus) {
		this.permissionMenus = permissionMenus;
	}

	public Menu getDefaultMenu() {
		return defaultMenu;
	}

	public void setDefaultMenu(Menu defaultMenu) {
		this.defaultMenu = defaultMenu;
	}

}
