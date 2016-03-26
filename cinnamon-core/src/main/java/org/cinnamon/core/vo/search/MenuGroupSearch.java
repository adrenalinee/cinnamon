package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
public class MenuGroupSearch {
	
	Long menuGroupId;
	
	String siteId;
	
	String dimension;
	
	Long hasdMenuId;

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public Long getHasdMenuId() {
		return hasdMenuId;
	}

	public void setHasdMenuId(Long hasdMenuId) {
		this.hasdMenuId = hasdMenuId;
	}
	
	
}
