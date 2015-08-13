package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
public class MenuSearch {
	
	Long menuId;
	
	String name;
	
	String uri;
	
	Long parentMenuId;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	
	
}
