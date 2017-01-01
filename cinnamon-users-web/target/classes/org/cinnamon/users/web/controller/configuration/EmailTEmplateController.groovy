package org.cinnamon.users.web.controller.configuration

import org.cinnamon.web.configuration.controller.BasePageController;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author 신동성
 * @since 2016. 3. 21.
 */
@Controller
@RequestMapping(value="/configuration/email/templates")
class EmailTEmplateController extends BasePageController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@RequestMapping(value="**")
	def index() {
		logger.info("start");
		"configuration/email/templates"
	}
}
