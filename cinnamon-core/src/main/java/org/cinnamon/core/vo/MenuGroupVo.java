package org.cinnamon.core.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * create date: 2015. 3. 25.
 * @author 동성
 *
 */
public class MenuGroupVo {
	
////	@Null
//	Long menuGroupId;
//	
//	@NotEmpty
//	String siteId;
	
	@NotEmpty
	@Size(max=100)
	String name;
	
	@NotEmpty
	@Size(max=50)
	String dimension;
	
	@Size(max=100)
	String label;
	
	@Size(max=200)
	String defaultPage;
	
	@Size(max=4000)
	String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}

//	public Long getMenuGroupId() {
//		return menuGroupId;
//	}
//
//	public void setMenuGroupId(Long menuGroupId) {
//		this.menuGroupId = menuGroupId;
//	}
//
//	public String getSiteId() {
//		return siteId;
//	}
//
//	public void setSiteId(String siteId) {
//		this.siteId = siteId;
//	}
	
}
