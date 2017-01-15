package org.cinnamon.web.configuration.controller.settings

import org.cinnamon.web.configuration.controller.BasePageController;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 27.
 */
@Controller
@RequestMapping("/settings/password")
class PasswordController extends BasePageController {
	
	@RequestMapping("**")
	def users() {
		"settings/password"
	}
}
