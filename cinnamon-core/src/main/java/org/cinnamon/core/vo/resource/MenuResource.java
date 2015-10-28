package org.cinnamon.core.vo.resource;

import java.util.List;

import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.MenuType;

/**
 * 
 * @author shindongseong
 *
 */
public class MenuResource {
	
	Long menuId;
	
	String name;
	
	String iconClass;
	
	String uri;
	
	String toolip;
	
	MenuType type;
	
	MenuPosition position;
	
	Integer orders;
	
	List<MenuResource> childs;

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

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getToolip() {
		return toolip;
	}

	public void setToolip(String toolip) {
		this.toolip = toolip;
	}

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public MenuPosition getPosition() {
		return position;
	}

	public void setPosition(MenuPosition position) {
		this.position = position;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public List<MenuResource> getChilds() {
		return childs;
	}

	public void setChilds(List<MenuResource> childs) {
		this.childs = childs;
	}
	
	
}
