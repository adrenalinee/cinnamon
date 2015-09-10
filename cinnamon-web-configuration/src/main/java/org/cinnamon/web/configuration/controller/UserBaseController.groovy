package org.cinnamon.web.configuration.controller

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.service.UserBaseService;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping;;

/**
 * 
 *
 * created date: 2015. 9. 9.
 * @author 신동성
 */
@Controller
@RequestMapping("/configuration")
class UserBaseController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	UserBaseService<UserBase> userService
	
	@RequestMapping("users/**")
	def users(Pageable pageable) {
		"configuration/users"
	}
}
