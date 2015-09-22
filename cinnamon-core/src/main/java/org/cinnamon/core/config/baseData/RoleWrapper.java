package org.cinnamon.core.config.baseData;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Role;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class RoleWrapper {
	
	Role role;
	
	List<UserGroupWrapper> userGroupWrappers = new LinkedList<>();
	
	RoleWrapper(String name, String authority) {
		role = new Role();
		role.setName(name);
		role.setAuthority(authority);
	}
	
	public RoleWrapper description(String description) {
		role.setDescription(description);
		return this;
	}
	
	public RoleWrapper addUserGroup(UserGroupWrapper... userGroupWrappers) {
		this.userGroupWrappers.addAll(Arrays.asList(userGroupWrappers));
		return this;
	}
}