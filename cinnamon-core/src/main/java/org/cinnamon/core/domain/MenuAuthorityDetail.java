package org.cinnamon.core.domain;

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
public class MenuAuthorityDetail {
	
	@Id
	@GeneratedValue
	Long roleMenuDetailId;
	
	@ManyToOne
	MenuAuthority roleMenu;
	
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

	public MenuAuthority getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(MenuAuthority roleMenu) {
		this.roleMenu = roleMenu;
	}
	
	
}
