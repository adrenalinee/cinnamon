package org.cinnamon.web.configuration.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@Controller
class ConfigurationController {
	
	@RequestMapping("/configuration")
	String index() {
		"redirect:/configuration/users"
	}
	
	@RequestMapping(value="/configuration/partials/**")
	void partials() {
		
	}
	
	@RequestMapping(value="/configuration/directives/**")
	void directives() {
		
	}
}
