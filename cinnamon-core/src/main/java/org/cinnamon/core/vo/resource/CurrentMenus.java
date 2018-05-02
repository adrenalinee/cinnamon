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
	
	List<MenuR> actives = Lists.newArrayList();
	
	List<MenuR> sidebar =  Lists.newArrayList();
	
	List<MenuR> headerRight =  Lists.newArrayList();
	
	List<MenuR> headerLeft =  Lists.newArrayList();
	
	public List<MenuR> getActives() {
		return actives;
	}

	public void setActives(List<MenuR> actives) {
		this.actives = actives;
	}
	
	public void addSidebar(MenuR menuResource) {
		sidebar.add(menuResource);
	}
	
	public void addHeaderRight(MenuR menuResource) {
		headerRight.add(menuResource);
	}
	
	public void addHeaderLeft(MenuR menuResource) {
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

	public List<MenuR> getSidebar() {
		return sidebar;
	}

	public void setSidebar(List<MenuR> sidebar) {
		this.sidebar = sidebar;
	}

	public List<MenuR> getHeaderRight() {
		return headerRight;
	}

	public void setHeaderRight(List<MenuR> headerRight) {
		this.headerRight = headerRight;
	}

	public List<MenuR> getHeaderLeft() {
		return headerLeft;
	}

	public void setHeaderLeft(List<MenuR> headerLeft) {
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
