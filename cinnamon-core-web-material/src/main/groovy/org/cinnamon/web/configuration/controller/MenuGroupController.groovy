package org.cinnamon.web.configuration.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author 신동성
 * @since 2016. 3. 2.
 */
@Controller
@RequestMapping("/configuration/menuGroups")
class MenuGroupController {
	
	@RequestMapping("**")
	def main() {
		"configuration/menuGroups"
	}
}
