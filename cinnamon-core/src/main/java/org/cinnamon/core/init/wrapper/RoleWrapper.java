package org.cinnamon.core.init.wrapper;

import org.cinnamon.core.domain.Role;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class RoleWrapper {
	
	Role role;
	
	RoleWrapper(String name, String authority) {
		role = new Role();
		role.setName(name);
		role.setAuthority(authority);
	}
	
	public RoleWrapper description(String description) {
		role.setDescription(description);
		return this;
	}
}
