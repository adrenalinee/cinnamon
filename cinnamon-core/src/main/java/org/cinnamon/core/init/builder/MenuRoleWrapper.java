package org.cinnamon.core.init.builder;

import org.cinnamon.core.domain.RoleMenu;

/**
 * 
 * create date: 2015. 5. 27.
 * @author 신동성
 *
 */
public class MenuRoleWrapper {
	
	private MenuWrapper menuWrapper;
	
	private RoleMenu permissionMenu;
	
	private String authority;
	
	MenuRoleWrapper(MenuWrapper menuWrapper, String authority) {
		this.menuWrapper = menuWrapper;
		this.authority = authority;
		
		permissionMenu = new RoleMenu();
	}
	
	RoleMenu permissionMenu() {
		return permissionMenu;
	}
	
	public MenuRoleWrapper denyAll() {
		permissionMenu.setPermitRoot(false);
		menuWrapper.menuRoles().remove(authority);
		return this;
	}
	
	public MenuRoleWrapper denyRoot() {
		permissionMenu.denyRoot();
		return this;
	}
	
	
	public MenuRoleWrapper permitElse() {
		permissionMenu.permitElse();
		return this;
	}
	
	public MenuRoleWrapper permitRoot() {
		permissionMenu.permitRoot();
		return this;
	}
	
	public MenuRoleWrapper permitCreate() {
		permissionMenu.permitCreate();
		return this;
	}
	
	public MenuRoleWrapper permitView() {
		permissionMenu.permitView();
		return this;
	}
	
	public MenuRoleWrapper permitModify() {
		permissionMenu.permitModify();
		return this;
	}
	
	public MenuRoleWrapper permitSearch() {
		permissionMenu.permitSearch();
		return this;
	}
	
	public MenuRoleWrapper permit(String name) {
		permissionMenu.permit(name);
		return this;
	}
	
	
	
	
	public MenuRoleWrapper role(String authority) {
		return menuWrapper.role(authority);
	}
	
	public MenuGroupWrapper and() {
		return menuWrapper.and();
	}
}
