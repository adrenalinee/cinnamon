package org.cinnamon.core.init.wrapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.enumeration.MenuType;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class MenuWrapper {
	
	Menu menu;
	
	List<MenuWrapper> childMenuWrappers = new LinkedList<>();
	
	List<String> grantedAuthorities = new LinkedList<>();
	
//	MenuWrapper(String name, MenuPosition position) {
//		menu = new Menu();
//		menu.setName(name);
//		menu.setPosition(position);
//	}
	
	MenuWrapper(String name) {
		menu = new Menu();
		menu.setName(name);
	}
	
	public MenuWrapper uri(String uri) {
		menu.setUri(uri);
		return this;
	}
	
	public MenuWrapper iconClass(String iconClass) {
		menu.setIconClass(iconClass);
		return this;
	}
	
	public MenuWrapper description(String description) {
		menu.setDescription(description);
		return this;
	}
	
	public MenuWrapper type(MenuType type) {
		menu.setType(type);
		return this;
	}
	
	public MenuWrapper addGrantedAuthorities(Object... authorities) {
		Arrays.asList(authorities).forEach(authority -> {
			grantedAuthorities.add(authority.toString());
		});
		return this;
	}
	
	public MenuWrapper addChilds(MenuWrapper... menuWrappers) {
		childMenuWrappers.addAll(Arrays.asList(menuWrappers));
		return this;
	}
}
