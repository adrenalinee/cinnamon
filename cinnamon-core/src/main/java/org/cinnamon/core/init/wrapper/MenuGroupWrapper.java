package org.cinnamon.core.init.wrapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.enumeration.MenuPosition;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class MenuGroupWrapper {
	
	MenuGroup menuGroup;
	
	List<MenuWrapper> menuWrappers = new LinkedList<>();
	
	MenuGroupWrapper(String name, String dimension) {
		menuGroup = new MenuGroup();
		menuGroup.setName(name);
		menuGroup.setDimension(dimension);
	}
	
	public MenuGroupWrapper description(String description) {
		menuGroup.setDescription(description);
		return this;
	}
	
	public MenuGroupWrapper addMenusAtSidebar(MenuWrapper... menuWrappers) {
		Arrays.asList(menuWrappers).forEach(menuWrapper -> {
			menuWrapper.menu.setPosition(MenuPosition.sidebar);
			this.menuWrappers.add(menuWrapper);
		});
		return this;
	}
	
	public MenuGroupWrapper addMenusAtHeaderRight(MenuWrapper... menuWrappers) {
		Arrays.asList(menuWrappers).forEach(menuWrapper -> {
			menuWrapper.menu.setPosition(MenuPosition.headerRight);
			this.menuWrappers.add(menuWrapper);
		});
		return this;
	}
	
	public MenuGroupWrapper addMenusAtHeaderLeft(MenuWrapper... menuWrappers) {
		Arrays.asList(menuWrappers).forEach(menuWrapper -> {
			menuWrapper.menu.setPosition(MenuPosition.headerLeft);
			this.menuWrappers.add(menuWrapper);
		});
		return this;
	}
}
