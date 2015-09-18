package org.cinnamon.core.config.baseData;

import org.cinnamon.core.domain.UserGroup;

/**
 * 
 *
 * created date: 2015. 9. 18.
 * @author 신동성
 */
public class UserGroupWrapper {
	
	UserGroup userGroup;
	
	boolean isDefault;
	
	UserGroupWrapper(String name) {
		userGroup = new UserGroup();
		userGroup.setName(name);
	}
	
	public UserGroupWrapper setDefault() {
		isDefault = true;
		return this;
	}
}
