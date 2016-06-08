package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
public class SiteSearch {
	
	String keyword;
	
	String siteId;
	
	String name;
	
	Long menuGroupId;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
