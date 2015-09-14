package org.cinnamon.core.init.builder2;

import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.MenuType;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class MenuWrapper {
	
	Menu menu;
	
	List<String> grantedAuthorities = new LinkedList<>();
	
	MenuWrapper(String name, MenuPosition position) {
		menu = new Menu();
		menu.setName(name);
		menu.setPosition(position);
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
	
	public MenuWrapper addGrantedAuthority(String authority) {
		grantedAuthorities.add(authority);
		return this;
	}
}
