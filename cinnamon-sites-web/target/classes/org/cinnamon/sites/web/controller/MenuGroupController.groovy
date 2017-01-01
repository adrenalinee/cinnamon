package org.cinnamon.sites.web.controller

import org.cinnamon.web.configuration.controller.BasePageController;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author 신동성
 * @since 2016. 3. 2.
 */
@Controller
@RequestMapping("/configuration/menuGroups")
class MenuGroupController extends BasePageController {
	
	@RequestMapping("**")
	def main() {
		"configuration/menuGroups"
	}
}
