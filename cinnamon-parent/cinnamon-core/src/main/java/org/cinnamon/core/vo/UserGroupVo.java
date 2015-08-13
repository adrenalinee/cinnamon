package org.cinnamon.core.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * create date: 2015. 3. 17.
 * @author 동성
 *
 */
public class UserGroupVo {
	
	@NotEmpty
	@Size(max=100)
	String name;
	
	@Size(max=1000)
	String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
