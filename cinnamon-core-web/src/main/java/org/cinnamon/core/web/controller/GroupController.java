package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 신동성
 * @since 2016. 2. 18.
 */
@Controller
@RequestMapping("/configuration/groups")
class GroupController extends BasePageController {
	
	@RequestMapping("**")
	String main() {
		return "configuration/groups";
	}
}
