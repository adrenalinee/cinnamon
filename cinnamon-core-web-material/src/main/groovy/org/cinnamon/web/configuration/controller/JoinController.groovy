package org.cinnamon.web.configuration.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 *
 * created date: 2016. 3. 27.
 * @author 신동성
 */
@Controller
class JoinController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@RequestMapping(value="/join")
	def join() {
		logger.info("start")
		"configuration/join"
	}
}
