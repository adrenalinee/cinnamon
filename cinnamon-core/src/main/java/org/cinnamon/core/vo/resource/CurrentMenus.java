package org.cinnamon.core.vo.resource;

import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Menu;

/**
 * 
 * @author 신동성
 * @since 2015. 10. 30.
 */
public class CurrentMenus {
	
	String dimension;
	
	String name;
	
	List<Menu> actives = new LinkedList<>();
	
	List<Menu> sidebar = new LinkedList<>();
	
	List<Menu> headerRight = new LinkedList<>();
	
	List<Menu> headerLeft = new LinkedList<>();
	
	public List<Menu> getActives() {
		return actives;
	}

	public void setActives(List<Menu> actives) {
		this.actives = actives;
	}
	
	public void addSidebar(Menu menuResource) {
		sidebar.add(menuResource);
	}
	
	public void addHeaderRight(Menu menuResource) {
		headerRight.add(menuResource);
	}
	
	public void addHeaderLeft(Menu menuResource) {
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

	public List<Menu> getSidebar() {
		return sidebar;
	}

	public void setSidebar(List<Menu> sidebar) {
		this.sidebar = sidebar;
	}

	public List<Menu> getHeaderRight() {
		return headerRight;
	}

	public void setHeaderRight(List<Menu> headerRight) {
		this.headerRight = headerRight;
	}

	public List<Menu> getHeaderLeft() {
		return headerLeft;
	}

	public void setHeaderLeft(List<Menu> headerLeft) {
		this.headerLeft = headerLeft;
	}
	
}
