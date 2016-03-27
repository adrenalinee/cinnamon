package org.cinnamon.web.configuration.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller

/**
 * 
 *
 * created date: 2016. 3. 27.
 * @author 신동성
 */
@Controller
class JoinController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	void join() {
		logger.info("start")
		"configuration/join"
	}
}
