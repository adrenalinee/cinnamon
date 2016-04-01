package org.cinnamon.apps.domain.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 정명성
 * create date : 2016. 4. 1.
 * org.cinnamon.apps.domain.vo.ApplicationVo.java
 */
public class ApplicationVo {
	
	@NotEmpty
	@NotNull
	@Size(max=100)
	String name;
	
	@NotEmpty
	@NotNull
	@Size(max=4000)
	String description;
}
