package org.cinnamon.web.configuration.controller

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author shindongseong
 *
 */
@Controller
@RequestMapping("/configuration/menus")
class MenuController {
	
	@RequestMapping("**")
	def users() {
		"configuration/menus"
	}
}
