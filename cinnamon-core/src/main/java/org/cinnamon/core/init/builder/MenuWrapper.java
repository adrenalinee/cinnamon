package org.cinnamon.core.init.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.enumeration.MenuPosition;



/**
 * 
 * create date: 2015. 5. 26.
 * @author 신동성
 *
 */
public class MenuWrapper {
	
	private MenuGroupWrapper menuGroup;
	
//	private String uri;
	
	private Menu menu;
	
	private MenuWrapper parentMenu;
	
	private Map<String, MenuWrapper> childMenus = new LinkedHashMap<String, MenuWrapper>();
	
	private Map<String, MenuRoleWrapper> menuRoles = new LinkedHashMap<String, MenuRoleWrapper>();
	
	private int childOrders;
	
	MenuWrapper(MenuGroupWrapper menuGroup, MenuPosition position, String name, String uri) {
		this(menuGroup, null, position, name, uri);
	}
	
	MenuWrapper(MenuGroupWrapper menuGroup, MenuWrapper parent, MenuPosition position, String name, String uri) {
		this.menuGroup = menuGroup;
		this.parentMenu = parent;
		
		menu = new Menu();
		menu.setName(name);
		menu.setUri(uri);
		
		menu.setPosition(position);
		if (parent == null) {
			menu.setOrders(menuGroup.nextOrder());
		} else {
			menu.setOrders(parent.nextOrder());
//			menu.setParent(parent.menu());
		}
		
//		menuGroup.menuGroupRoles().forEach((authority, mgrw) -> {
//			MenuRoleWrapper mrw = MenuWrapper.this.role(authority);
//			if (mgrw.readPower()) {
//				mrw.permitRead();
//			}
//			if (mgrw.createPower()) {
//				mrw.permitCreatePower();
//			}
//			if (mgrw.updatePower()) {
//				mrw.permitUpdatePower();
//			}
//			if (mgrw.deletePower()) {
//				mrw.permitDeletePower();
//			}
//		}); 
	}
	
	Menu menu() {
		return menu;
	}
	
	Map<String, MenuRoleWrapper> menuRoles() {
		return menuRoles;
	}
	
	Map<String, MenuWrapper> childMenus() {
		return childMenus;
	}
	
	void applyMenuGroupPermit() {
		menuGroup.menuGroupRoles().forEach((authority, mgrw) -> {
			MenuRoleWrapper mrw = MenuWrapper.this.role(authority);
			
			if (mgrw.isPermitElse()) {
				mrw.permitElse();
			}
			if (mgrw.isPermitRoot()) {
				mrw.permitRoot();
			}
			if (mgrw.isPermitCreate()) {
				mrw.permitCreate();
			}
			if (mgrw.isPermitView()) {
				mrw.permitView();
			}
			if (mgrw.isPermitModify()) {
				mrw.permitModify();
			}
			if (mgrw.isPermitSearch()) {
				mrw.permitSearch();
			}
			
			
			
//			mrw.permitRead(mgrw.readPower());
//			mrw.permitCreate(mgrw.createPower());
//			mrw.permitUpdate(mgrw.updatePower());
//			mrw.permitDelete(mgrw.deletePower());
			
//			if ("메뉴".equals(menu.getName())) {
//				System.out.println("메뉴: " + authority);
//				System.out.println(ToStringBuilder.reflectionToString(mgrw));
//			}
		});
	}
	
	void clear() {
		menu = null;
		childMenus = null;
	}
	
	int nextOrder() {
		return childOrders++;
	}
	
	
	public MenuWrapper name(String name) {
		menu.setName(name);
		return this;
	}
	
	
	public MenuWrapper iconClass(String iconClass) {
		menu.setIconClass(iconClass);
		return this;
	}
	
	
	public MenuWrapper child(String name) {
		return child(name, null);
	}
	
	public MenuWrapper child(String name, String uri) {
		if (name == null) {
			throw new RuntimeException("menu 이름은 null이 되면 안됩니다. menu uri: " + uri);
		}
		
		String key = name;
		if (uri != null) {
			key += "-" + uri;
		} else {
			key += "-nullUrl";
		}
		
		MenuWrapper menuWrapper = childMenus.get(key);
		if (menuWrapper == null) {
			menuWrapper = new MenuWrapper(menuGroup, this, menu.getPosition(), name, uri);
			childMenus.put(key, menuWrapper);
		}
		menuWrapper.applyMenuGroupPermit();
		
		return menuWrapper;
	}
	
	
	public MenuWrapper parent() {
		return parentMenu;
	}
	
//	public MenuWrapper position(MenuPosition position) {
//		menu.setPosition(position);
//		return this;
//	}
	
	public MenuGroupWrapper and() {
		return menuGroup;
	}
	
	
	public MenuRoleWrapper role(String authority) {
		MenuRoleWrapper menuRole = menuRoles.get(authority);
		if (menuRole == null) {
			menuRole = new MenuRoleWrapper(this, authority);
			menuRoles.put(authority, menuRole);
		}
		
		return menuRole;
	}
}
