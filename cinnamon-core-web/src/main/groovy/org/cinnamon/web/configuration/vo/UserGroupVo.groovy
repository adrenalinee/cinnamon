package org.cinnamon.web.configuration.vo

import javax.validation.constraints.Size

import org.hibernate.validator.constraints.NotEmpty

/**
 * 
 * @author shindongseong
 * @since 2015. 12. 31.
 */
class UserGroupVo {
	
	@NotEmpty
	@Size(max=100)
	String name
	
	@Size(max=1000)
	String description
}
