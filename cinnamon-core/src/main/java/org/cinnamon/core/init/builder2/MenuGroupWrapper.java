package org.cinnamon.core.init.builder2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.MenuGroup;

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
	
	public MenuGroupWrapper addMenus(MenuWrapper... menuWrappers) {
		this.menuWrappers.addAll(Arrays.asList(menuWrappers));
		return this;
	}
}
