package org.cinnamon.users.web.controller.configuration

import org.cinnamon.web.configuration.controller.BasePageController;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 *
 * created date: 2015. 9. 9.
 * @author 신동성
 */
@Controller
@RequestMapping("/configuration/users")
class UserBaseController extends BasePageController {
	
	@RequestMapping("**")
	def main() {
		"configuration/users"
	}
}
