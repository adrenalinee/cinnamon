package org.cinnamon.web.configuration.controller

import org.springframework.data.domain.Pageable
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
class UserBaseController {
	
	@RequestMapping("**")
	def users(Pageable pageable) {
		"configuration/users"
	}
}
