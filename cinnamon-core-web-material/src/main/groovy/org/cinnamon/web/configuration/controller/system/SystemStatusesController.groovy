package org.cinnamon.web.configuration.controller.system

import org.cinnamon.web.configuration.controller.BasePageController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author 신동성
 * @since 2016. 6. 29.
 */
@Controller("cinnamon.system.systemStatusesController")
@RequestMapping("/configuration/system/statuses")
class SystemStatusesController extends BasePageController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@RequestMapping("**")
	def main() {
		logger.info("start")
		"configuration/system/statuses"
	}
}
