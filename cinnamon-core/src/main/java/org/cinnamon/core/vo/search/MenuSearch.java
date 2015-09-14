package org.cinnamon.core.vo.search;

import org.cinnamon.core.domain.enumeration.MenuPosition;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
public class MenuSearch {
	
	Long menuId;
	
	String name;
	
	String uri;
	
	MenuPosition position;
	
	public MenuPosition getPosition() {
		return position;
	}

	public void setPosition(MenuPosition position) {
		this.position = position;
	}

	Long parentMenuId;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	
	
}
