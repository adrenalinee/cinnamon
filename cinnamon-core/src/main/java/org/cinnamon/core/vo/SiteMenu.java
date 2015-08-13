package org.cinnamon.core.vo;

import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Menu;

/**
 * 
 * create date: 2015. 4. 1.
 * @author 동성
 *
 */
public class SiteMenu {
	
	String name;
	
	List<Menu> actives = new LinkedList<Menu>();
	
	List<Menu> sidebars = new LinkedList<Menu>();
	
	List<Menu> headerRights = new LinkedList<Menu>();
	
	List<Menu> headerLefts = new LinkedList<Menu>();

	public List<Menu> getActives() {
		return actives;
	}

	public void setActives(List<Menu> actives) {
		this.actives = actives;
	}

	public List<Menu> getSidebars() {
		return sidebars;
	}

	public void setSidebars(List<Menu> sidebars) {
		this.sidebars = sidebars;
	}

	public List<Menu> getHeaderRights() {
		return headerRights;
	}

	public void setHeaderRights(List<Menu> headerRights) {
		this.headerRights = headerRights;
	}

	public List<Menu> getHeaderLefts() {
		return headerLefts;
	}

	public void setHeaderLefts(List<Menu> headerLefts) {
		this.headerLefts = headerLefts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
