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
public class RoleMenuDetail {
	
	@Id
	@GeneratedValue
	Long roleMenuDetailId;
	
	@ManyToOne
	RoleMenu roleMenu;
	
	String name;
	
	boolean permit = true;

	public Long getRoleMenuDetailId() {
		return roleMenuDetailId;
	}

	public void setRoleMenuDetailId(Long roleMenuDetailId) {
		this.roleMenuDetailId = roleMenuDetailId;
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

	public RoleMenu getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(RoleMenu roleMenu) {
		this.roleMenu = roleMenu;
	}
	
	
}
