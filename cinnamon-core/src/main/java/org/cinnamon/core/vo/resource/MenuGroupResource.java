package org.cinnamon.core.vo.resource;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author 신동성
 * @since 2015. 10. 30.
 */
public class MenuGroupResource {
	
	String dimension;
	
	String name;
	
	List<MenuResource> sidebar = new LinkedList<>();
	
	List<MenuResource> headerRight = new LinkedList<>();
	
	List<MenuResource> headerLeft = new LinkedList<>();
	
	public void addSidebar(MenuResource menuResource) {
		sidebar.add(menuResource);
	}
	
	public void addHeaderRight(MenuResource menuResource) {
		headerRight.add(menuResource);
	}
	
	public void addHeaderLeft(MenuResource menuResource) {
		headerLeft.add(menuResource);
	}
	
	
	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuResource> getSidebar() {
		return sidebar;
	}

	public void setSidebar(List<MenuResource> sidebar) {
		this.sidebar = sidebar;
	}

	public List<MenuResource> getHeaderRight() {
		return headerRight;
	}

	public void setHeaderRight(List<MenuResource> headerRight) {
		this.headerRight = headerRight;
	}

	public List<MenuResource> getHeaderLeft() {
		return headerLeft;
	}

	public void setHeaderLeft(List<MenuResource> headerLeft) {
		this.headerLeft = headerLeft;
	}
	
}
