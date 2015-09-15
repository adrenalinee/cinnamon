package org.cinnamon.core.init.wrapper;

import org.cinnamon.core.domain.Permission;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class RoleWrapper {
	
	Permission role;
	
	RoleWrapper(String name, String authority) {
		role = new Permission();
		role.setName(name);
		role.setAuthority(authority);
	}
	
	public RoleWrapper description(String description) {
		role.setDescription(description);
		return this;
	}
}
