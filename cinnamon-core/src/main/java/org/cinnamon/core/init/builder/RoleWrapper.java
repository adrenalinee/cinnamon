package org.cinnamon.core.init.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import org.cinnamon.core.domain.Role;

/**
 * 
 * create date: 2015. 5. 27.
 * @author 신동성
 *
 */
public class RoleWrapper {
	
//	private MenuWrapper menuWrapper;
	
	private Role permission;
	
//	private PermissionMenu permissionMenu;
	
	private Map<String, UserGroupWrapper> userGroups = new LinkedHashMap<String, UserGroupWrapper>();
	
	SiteBuilder siteBuilder;
	
//	RoleWrapper(MenuWrapper menuWrapper, String authority) {
//		this.menuWrapper = menuWrapper;
//		
//		permission = new Permission();
//		permission.setAuthority(authority);
//	}
	
	RoleWrapper(SiteBuilder siteBuilder, String authority) {
		this.siteBuilder = siteBuilder;
		
		permission = new Role();
		permission.setAuthority(authority);
	}
	
	Role permission() {
		return permission;
	}
	
//	PermissionMenu permissionMenu() {
//		return permissionMenu;
//	}
	
	Map<String, UserGroupWrapper> userGroups() {
		return userGroups;
	}
	
	
	
	
	
	public RoleWrapper name(String name) {
		permission.setName(name);
		return this;
	}
	
//	public RoleWrapper permitAll() {
//		permissionMenu.allPermission();
//		return this;
//	}
//	
//	public RoleWrapper permitRead() {
//		permissionMenu.setReadPower(true);
//		return this;
//	}
//	
//	public RoleWrapper permitCreatePower() {
//		permissionMenu.setCreatePower(true);
//		return this;
//	}
//	
//	public RoleWrapper permitUpdatePower() {
//		permissionMenu.setUpdatePower(true);
//		return this;
//	}
//	
//	public RoleWrapper permitDeletePower() {
//		permissionMenu.setDeletePower(true);
//		return this;
//	}
	
	
	public RoleWrapper userGroup(String name, boolean isDefault) {
		UserGroupWrapper userGroup = userGroups.get(name);
		if (userGroup == null) {
			userGroup = new UserGroupWrapper(name);
			userGroups.put(name, userGroup);
		}
		
		if (isDefault) {
			userGroup.setDefault();
		}
		
		return this;
	}
	
	public RoleWrapper userGroup(String name) {
		return userGroup(name, false);
	}
	
	
	public RoleWrapper role(String authority) {
		return siteBuilder.role(authority);
	}
	
//	public MenuGroupWrapper and() {
//		return menuWrapper.and();
//	}
}
