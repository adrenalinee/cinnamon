package org.cinnamon.core.vo.resource;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author 신동성
 * @since 2015. 10. 30.
 */
public class DimensionMenusResource {
	
	String dimension;
	
	String name;
	
	List<MenuResource> sidebar;
	
	List<MenuResource> headerRights;
	
	List<MenuResource> headerLefts;

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public List<MenuResource> getSidebar() {
		return sidebar;
	}

	public void setSidebar(List<MenuResource> sidebar) {
		this.sidebar = sidebar;
	}

	public List<MenuResource> getHeaderRights() {
		return headerRights;
	}

	public void setHeaderRights(List<MenuResource> headerRights) {
		this.headerRights = headerRights;
	}

	public List<MenuResource> getHeaderLefts() {
		return headerLefts;
	}

	public void setHeaderLefts(List<MenuResource> headerLefts) {
		this.headerLefts = headerLefts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
