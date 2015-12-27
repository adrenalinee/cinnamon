package org.cinnamon.web.configuration.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping;;

/**
 * 
 *
 * created date: 2015. 9. 24.
 * @author 신동성
 */
@Controller
class MainController {
	
	@RequestMapping("/")
	String index() {
		"redirect:/configuration"
	}
	
	@RequestMapping(value="/partials/**")
	void partials() {
		
	}
	
	@RequestMapping(value="/directives/**")
	 void drective() {
		 
	 }
}
