package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 신동성
 * @since 2016. 3. 2.
 */
@Controller
@RequestMapping("/configuration/menus")
public class MenuController extends BasePageController {
	
	@RequestMapping("**")
	String main() {
		return "configuration/menus";
	}
}
