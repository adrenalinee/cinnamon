package org.cinnamon.core.vo;

import java.util.List;

import org.cinnamon.core.domain.enumeration.MenuType;

/**
 * 
 * @author shindongseong
 * @since 2015. 12. 13.
 */
public class LayoutMenu {
	
	String name;
	
	String iconClass;
	
	String uri;
	
	String toolip;
	
	MenuType type;
	
	List<LayoutMenu> childs;

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

	public List<LayoutMenu> getChilds() {
		return childs;
	}

	public void setChilds(List<LayoutMenu> childs) {
		this.childs = childs;
	}

}
