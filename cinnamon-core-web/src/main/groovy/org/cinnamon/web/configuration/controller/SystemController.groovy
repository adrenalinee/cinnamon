package org.cinnamon.web.configuration.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

//@Controller
//@RequestMapping(value="/configuration/system")
class SystemController extends BasePageController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
//	@RequestMapping(value="**")
	def index() {
		logger.info("start");
		"redirect:system/statuses"
	}
}
