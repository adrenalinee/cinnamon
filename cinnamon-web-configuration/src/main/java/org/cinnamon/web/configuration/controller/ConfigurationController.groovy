package org.cinnamon.web.configuration.controller

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.service.MenuGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping;;

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
	
	@RequestMapping("/configuration/partials/**")
	void partials() {
		
	}
}
