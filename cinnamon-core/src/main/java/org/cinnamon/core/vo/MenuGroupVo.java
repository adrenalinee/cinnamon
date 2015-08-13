package org.cinnamon.core.vo;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * create date: 2015. 3. 25.
 * @author 동성
 *
 */
public class MenuGroupVo {
	
	@Null
	Long menuGroupId;
	
	@NotEmpty
	String name;
	
	@NotEmpty
	String dimension;
	
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

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}
	
}
