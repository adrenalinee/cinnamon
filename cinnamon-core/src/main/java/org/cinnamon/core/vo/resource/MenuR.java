package org.cinnamon.core.vo.resource;

import org.cinnamon.core.domain.enumeration.MenuType;

import lombok.Data;

/**
 * 
 * @author shindongseong
 *
 */
@Data
public class MenuR {
	
	Long menuId;
	
	String name;
	
	String iconClass;
	
	String uri;
	
	String toolip;
	
	MenuType type;
	
	MenuGroupR menuGroup;
	
//	MenuPosition position;
	
//	Integer orders;
	
//	List<MenuR> childs;

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getIconClass() {
//		return iconClass;
//	}
//
//	public void setIconClass(String iconClass) {
//		this.iconClass = iconClass;
//	}
//
//	public String getUri() {
//		return uri;
//	}
//
//	public void setUri(String uri) {
//		this.uri = uri;
//	}
//
//	public String getToolip() {
//		return toolip;
//	}
//
//	public void setToolip(String toolip) {
//		this.toolip = toolip;
//	}
//
//	public MenuType getType() {
//		return type;
//	}
//
//	public void setType(MenuType type) {
//		this.type = type;
//	}
//
//	public List<MenuR> getChilds() {
//		return childs;
//	}
//
//	public void setChilds(List<MenuR> childs) {
//		this.childs = childs;
//	}
	
}
