package org.cinnamon.web.configuration.controller.settings

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 14.
 */
@Controller
class SettingController {
	
	@RequestMapping("/settings")
	def settings() {
		"redirect:/settings/account"
	}
	
	@RequestMapping(value="/settings/partials/**")
	void partials() {
		
	}
	
	@RequestMapping(value="/settings/directives/**")
	void directives() {
		
	}
}
