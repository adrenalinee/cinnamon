package org.cinnamon.web.configuration.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author 신동성
 * @since 2016. 4. 11.
 */
@Controller("cinnamon.accountController")
class AccountController extends BasePageController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@RequestMapping(value="/login")
	def login() {
		logger.info("start")
		"configuration/login"
	}
	
	@RequestMapping(value="/join")
	def join() {
		logger.info("start")
		"configuration/join"
		
		
	}
	
	
	
	@RequestMapping(value="/findPassword")
	def findPassword() {
		logger.info("start")
		"configuration/findPassword"
	}
}
