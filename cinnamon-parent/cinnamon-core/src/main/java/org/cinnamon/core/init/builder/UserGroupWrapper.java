package org.cinnamon.core.init.builder;

import org.cinnamon.core.domain.UserGroup;

/**
 * 
 * create date: 2015. 5. 27.
 * @author 신동성
 *
 */
public class UserGroupWrapper {
	
	private UserGroup userGroup;
	
	private boolean isDefault;
	
	UserGroupWrapper(String name) {
		
		userGroup = new UserGroup();
		userGroup.setName(name);
	}
	
	UserGroup userGroup() {
		return userGroup;
	}
	
	boolean isDefault() {
		return isDefault;
	}
	
	void setDefault() {
		isDefault = true;
	}
}
