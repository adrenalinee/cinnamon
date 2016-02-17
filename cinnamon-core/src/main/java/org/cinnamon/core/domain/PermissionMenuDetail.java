package org.cinnamon.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * create date: 2015. 6. 1.
 * @author 신동성
 *
 */
@Entity
@Table(name="permission_menu_detail")
public class PermissionMenuDetail {
	
	@Id
	@GeneratedValue
//	@Column(name="permission_menu_detail_id")
//	Long menuAuthorityDetailId;
	Long permissionMenuDetailId;
	
	@ManyToOne
	PermissionMenu permissionMenu;
	
	@Column(length=100)
	String name;
	
	boolean permit = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPermit() {
		return permit;
	}

	public void setPermit(boolean permit) {
		this.permit = permit;
	}

//	public Long getMenuAuthorityDetailId() {
//		return menuAuthorityDetailId;
//	}
//
//	public void setMenuAuthorityDetailId(Long menuAuthorityDetailId) {
//		this.menuAuthorityDetailId = menuAuthorityDetailId;
//	}

	public PermissionMenu getPermissionMenu() {
		return permissionMenu;
	}

	public void setPermissionMenu(PermissionMenu permissionMenu) {
		this.permissionMenu = permissionMenu;
	}

	public Long getPermissionMenuDetailId() {
		return permissionMenuDetailId;
	}

	public void setPermissionMenuDetailId(Long permissionMenuDetailId) {
		this.permissionMenuDetailId = permissionMenuDetailId;
	}

//	public PermissionMenu getMenuAuthority() {
//		return menuAuthority;
//	}
//
//	public void setMenuAuthority(PermissionMenu menuAuthority) {
//		this.menuAuthority = menuAuthority;
//	}
	
	
}
