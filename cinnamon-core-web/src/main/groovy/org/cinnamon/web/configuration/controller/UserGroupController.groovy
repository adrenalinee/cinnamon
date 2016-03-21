package org.cinnamon.web.configuration.controller

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author shindongseong
 * @since 2015. 12. 25.
 */
@Controller
@RequestMapping("/configuration/userGroups")
class UserGroupController {
	
	@RequestMapping("**")
	def users(Pageable pageable) {
		"configuration/userGroups"
	}
}
