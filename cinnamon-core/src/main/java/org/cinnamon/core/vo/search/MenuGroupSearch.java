package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
public class MenuGroupSearch {
	
	Long menuGroupId;
	
	String site;
	
	String dimension;
	
	String position;
	
	Long includedMenuId;

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getIncludedMenuId() {
		return includedMenuId;
	}

	public void setIncludedMenuId(Long includedMenuId) {
		this.includedMenuId = includedMenuId;
	}
	
	
}
