package org.cinnamon.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/configuration/email")
public class EmailController extends BasePageController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="**")
	String index() {
		logger.info("start");
		return "redirect:email/servers";
	}
}
