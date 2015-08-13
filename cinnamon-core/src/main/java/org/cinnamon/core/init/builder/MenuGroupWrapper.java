package org.cinnamon.core.init.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.enumeration.MenuPosition;

/**
 * 
 * create date: 2015. 5. 26.
 * @author 신동성
 *
 */
public class MenuGroupWrapper {
	
//	private String dimension;
	
	private MenuPosition position;

	private MenuGroup menuGroup;
	
	private Map<String, MenuWrapper> menus = new LinkedHashMap<String, MenuWrapper>();
	
	private Map<String, MenuGroupRoleWrapper> menuGroupRoles = new LinkedHashMap<String, MenuGroupRoleWrapper>();
	
	private String selectedAuthority;
	
	private int orders;
	
//	private int noUriMenuCount;
	
	
	MenuGroupWrapper(String dimension, MenuPosition position) {
//		this.dimension = dimension;
		this.position = position;
		menuGroup = new MenuGroup();
		menuGroup.setDimension(dimension);
	}
	
	MenuGroup menuGroup() {
		return menuGroup;
	}
	
	Map<String, MenuWrapper> menus() {
		return menus;
	}
	
	Map<String, MenuGroupRoleWrapper> menuGroupRoles() {
		return menuGroupRoles;
	}
	
	void clear() {
		menuGroup = null;
		menus = null;
	}
	
	void clearMenuGroupRoles() {
		menuGroupRoles = new LinkedHashMap<String, MenuGroupRoleWrapper>();
	}
	
	int nextOrder() {
		return orders++;
	}
	
	void position(MenuPosition position) {
		this.position = position;
	}
	
	
	public MenuGroupWrapper name(String name) {
		menuGroup.setName(name);
		return this;
	}
	
//	public MenuWrapper menu() {
//		return menu(null);
//	}
	
	
	public MenuWrapper menu(String name) {
		return menu(name, null);
	}
	
	public MenuWrapper menu(String name, String uri) {
		if (name == null) {
			throw new RuntimeException("menu 이름은 null이 되면 안됩니다. menu uri: " + uri);
		}
		
		String key = name;
		if (uri != null) {
			key += "-" + uri;
		} else {
			key += "-nullUrl";
		}
		
		
		MenuWrapper menu = menus.get(key);
		if (menu == null) {
			menu = new MenuWrapper(this, position, name, uri);
			menus.put(key, menu);
		}
		menu.applyMenuGroupPermit();
		
//		if ("메뉴".equals(name)) {
//			menuGroupRoles.forEach((a, r) -> {
//				System.out.print("authority: " + a);
//				System.out.print(", read: " + r.readPower());
//				System.out.print(", create: " + r.createPower());
//				System.out.print(", update: " + r.updatePower());
//				System.out.println(", delete: " + r.deletePower());
//			});
//			
//			
//			System.out.println("MenuWrapper: " + menu);
//		}
		
		return menu;
	}
	
	
	public MenuGroupWrapper role(String authority) {
		this.selectedAuthority = authority;
		
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(authority);
		if (menuGroupRole == null) {
			menuGroupRole = new MenuGroupRoleWrapper(authority);
			menuGroupRoles.put(authority, menuGroupRole);
		}
		
		return this;
	}
	
	
	public MenuGroupWrapper denyElse() {
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
		if (menuGroupRole == null) {
			//TODO role()을 먼저 호출해야 함을 알림
		}
		
		menuGroupRole.denyElse();
		return this;
	}
	
//	public MenuGroupWrapper permitAll() {
//		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
//		if (menuGroupRole == null) {
//			//TODO role()을 먼저 호출해야 함을 알림
//		}
//		
//		menuGroupRole.permitAll();
//		return this;
//	}
	
	public MenuGroupWrapper permitElse() {
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
		if (menuGroupRole == null) {
			//TODO role()을 먼저 호출해야 함을 알림
		}
		
		menuGroupRole.permitElse();
		return this;
	}
	
	public MenuGroupWrapper permitRoot() {
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
		if (menuGroupRole == null) {
			//TODO role()을 먼저 호출해야 함을 알림
		}
		menuGroupRole.permitRoot();
		return this;
	}
	
	public MenuGroupWrapper permitCreate() {
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
		if (menuGroupRole == null) {
			//TODO role()을 먼저 호출해야 함을 알림
		}
		menuGroupRole.permitCreate();
		return this;
	}
	
	public MenuGroupWrapper permitView() {
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
		if (menuGroupRole == null) {
			//TODO role()을 먼저 호출해야 함을 알림
		}
		menuGroupRole.permitView();
		return this;
	}
	
	public MenuGroupWrapper permitModify() {
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
		if (menuGroupRole == null) {
			//TODO role()을 먼저 호출해야 함을 알림
		}
		menuGroupRole.permitModify();
		return this;
	}
	
	public MenuGroupWrapper permitSearch() {
		MenuGroupRoleWrapper menuGroupRole = menuGroupRoles.get(selectedAuthority);
		if (menuGroupRole == null) {
			//TODO role()을 먼저 호출해야 함을 알림
		}
		menuGroupRole.permitSearch();
		return this;
	}
	
}
