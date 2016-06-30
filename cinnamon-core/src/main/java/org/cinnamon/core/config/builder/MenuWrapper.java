package org.cinnamon.core.config.builder;

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
	
	List<String> defultMenuForAuthorities = new LinkedList<>();
	
	
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
	
	/**
	 * 해당 메뉴에 접근허용할 권한 지정
	 * @param authority
	 * @return
	 */
	public MenuWrapper addGrantedAuthority(Object authority) {
		grantedAuthorities.add(authority.toString());
		return this;
	}
	
	/**
	 * 해당 메뉴에 접근허용할 권한 지정
	 * 기본 메뉴와 같이 지정
	 * @param authority
	 * @param isDefault
	 * @return
	 */
	public MenuWrapper addGrantedAuthority(Object authority, boolean isDefault) {
		grantedAuthorities.add(authority.toString());
		if (isDefault) {
			defultMenuForAuthorities.add(authority.toString());
		}
		
		return this;
	}
	
	public MenuWrapper addChilds(MenuWrapper... menuWrappers) {
		childMenuWrappers.addAll(Arrays.asList(menuWrappers));
		return this;
	}
}
