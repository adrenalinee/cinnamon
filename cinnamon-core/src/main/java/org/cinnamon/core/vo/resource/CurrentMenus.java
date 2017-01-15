package org.cinnamon.core.vo.resource;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 
 * @author 신동성
 * @since 2015. 10. 30.
 */
public class CurrentMenus {
	
	String dimension;
	
	String name;
	
	Integer current1depthIndex;
	
	Integer current2depthIndex;
	
	List<MenuResource> actives = Lists.newArrayList();
	
	List<MenuResource> sidebar =  Lists.newArrayList();
	
	List<MenuResource> headerRight =  Lists.newArrayList();
	
	List<MenuResource> headerLeft =  Lists.newArrayList();
	
	public List<MenuResource> getActives() {
		return actives;
	}

	public void setActives(List<MenuResource> actives) {
		this.actives = actives;
	}
	
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

	public Integer getCurrent1depthIndex() {
		return current1depthIndex;
	}

	public void setCurrent1depthIndex(Integer current1depthIndex) {
		this.current1depthIndex = current1depthIndex;
	}

	public Integer getCurrent2depthIndex() {
		return current2depthIndex;
	}

	public void setCurrent2depthIndex(Integer current2depthIndex) {
		this.current2depthIndex = current2depthIndex;
	}
	
}
