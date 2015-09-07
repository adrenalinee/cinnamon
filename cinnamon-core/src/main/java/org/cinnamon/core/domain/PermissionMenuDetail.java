package org.cinnamon.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * create date: 2015. 6. 1.
 * @author 신동성
 *
 */
@Entity
public class PermissionMenuDetail {
	
	@Id
	@GeneratedValue
	Long permissionMenuDetailId;
	
	@ManyToOne
	PermissionMenu permissionMenu;
	
	String name;
	
	boolean permit = true;

	public Long getPermissionMenuDetailId() {
		return permissionMenuDetailId;
	}

	public void setPermissionMenuDetailId(Long permissionMenuDetailId) {
		this.permissionMenuDetailId = permissionMenuDetailId;
	}

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

	public PermissionMenu getPermissionMenu() {
		return permissionMenu;
	}

	public void setPermissionMenu(PermissionMenu permissionMenu) {
		this.permissionMenu = permissionMenu;
	}
	
	
}
