package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 29.
 */
@Controller
@RequestMapping("/configuration/sites")
class SiteController extends BasePageController {
	
	@RequestMapping("**")
	String main() {
		return "configuration/sites";
	}
}
