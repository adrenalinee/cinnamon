package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 3. 17.
 * @author 동성
 *
 */
public class UserGroupSearch {
	
	Long userGroupId;
	
	String name;
	
//	Long permissionId;
	
	String authority;

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
}
