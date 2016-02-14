package org.cinnamon.web.configuration.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 14.
 */
@Controller
@RequestMapping("/configuration/settings")
class SettingController {
	
	@RequestMapping("**")
	def settings() {
		"configuration/settings"
	}
}
