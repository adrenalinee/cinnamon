package org.cinnamon.web.configuration.controller.settings

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 23.
 */
@Controller
@RequestMapping("/settings/account")
class AccountController {
	
	@RequestMapping("**")
	def users() {
		"settings/account"
	}
}
