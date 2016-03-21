package org.cinnamon.web.configuration.controller.settings

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 14.
 */
@Controller
@RequestMapping("/settings")
class SettingController {
	
	@RequestMapping("**")
	def settings() {
		"redirect:/settings/account"
	}
	
	@RequestMapping(value="/configuration/partials/**")
	void partials() {
		
	}
	
	@RequestMapping(value="/configuration/directives/**")
	void directives() {
		
	}
}
