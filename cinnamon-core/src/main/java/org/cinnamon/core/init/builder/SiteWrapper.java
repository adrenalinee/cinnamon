package org.cinnamon.core.init.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import org.cinnamon.core.domain.Site;
import org.cinnamon.core.domain.enumeration.MenuPosition;

/**
 * 
 * create date: 2015. 5. 26.
 * @author 신동성
 *
 */
public class SiteWrapper {
	
//	private String siteId;
	
	private Site site;
	
	private Map<String, MenuGroupWrapper> menuGroups = new LinkedHashMap<String, MenuGroupWrapper>();
	
//	private Map<String, Map<MenuPosition, MenuGroupWrapper>> menuGroups = new LinkedHashMap<String, Map<MenuPosition, MenuGroupWrapper>>();
	
	SiteWrapper(String siteId) {
//		this.siteId = siteId;
		site = new Site();
		site.setSiteId(siteId);
	}
	
	Site site() {
		return site;
	}
	
	Map<String, MenuGroupWrapper> menuGroups() {
		return menuGroups;
	}
	
//	Map<String, Map<MenuPosition, MenuGroupWrapper>> menuGroups() {
//		return menuGroups;
//	}
	
	void clear() {
		site = null;
		menuGroups = null;
	}
	
	
	public SiteWrapper name(String name) {
		site.setName(name);
		return this;
	}
	
	public MenuGroupWrapper menuGroup(String dimension, MenuPosition position) {
//		Map<MenuPosition, MenuGroupWrapper> menuGroupPositionMap = menuGroups.get(dimension);
//		if (menuGroupPositionMap == null) {
//			menuGroupPositionMap = new LinkedHashMap<MenuPosition, MenuGroupWrapper>();
//			MenuGroupWrapper menuGroup = menuGroupPositionMap.get(position);
//			if (menuGroup == null) {
//				menuGroup = new MenuGroupWrapper(dimension, position);
//				menuGroupPositionMap.put(position, menuGroup);
//			}
//			
//		}
//		
//		return menuGroupPositionMap.get(position);
		
		
		MenuGroupWrapper menuGroup = menuGroups.get(dimension);
		if (menuGroup == null) {
			menuGroup = new MenuGroupWrapper(dimension, position);
			menuGroups.put(dimension, menuGroup);
		}
		
		menuGroup.clearMenuGroupRoles();
		menuGroup.position(position);
		return menuGroup;
		
//		String key = dimension + "-" + position.name();
//		MenuGroupWrapper menuGroup = menuGroups.get(key);
//		if (menuGroup == null) {
//			menuGroup = new MenuGroupWrapper(dimension, position);
//			menuGroups.put(key, menuGroup);
//		}
//		
//		return menuGroups.get(key);
	}
	
}
