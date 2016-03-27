package org.cinnamon.web.configuration.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 *
 * created date: 2015. 9. 23.
 * @author 신동성
 */
@Controller
class LoginController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@RequestMapping(value="/login")
	def login() {
		logger.info("start")
		"configuration/login"
	}
}
