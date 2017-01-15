package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@Controller
public class ConfigurationController {
	
	@RequestMapping("/configuration")
	String index() {
		return "redirect:/configuration/users";
	}
	
	@RequestMapping(value="/configuration/partials/**")
	void partials() {
		
	}
	
	@RequestMapping(value="/configuration/directives/**")
	void directives() {
		
	}
}
