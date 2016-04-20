package org.cinnamon.core.vo.search;

import org.cinnamon.core.domain.enumeration.UseStatus;

/**
 * 
 * create date: 2015. 3. 17.
 * @author 동성
 *
 */
public class UserGroupSearch {
	
	String keyword;
	
	String userId;
	
	Long userGroupId;
	
	String name;
	
	String authority;
	
	UseStatus useStatus = UseStatus.enable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Long getPermissionId() {
//		return permissionId;
//	}
//
//	public void setPermissionId(Long permissionId) {
//		this.permissionId = permissionId;
//	}

	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
