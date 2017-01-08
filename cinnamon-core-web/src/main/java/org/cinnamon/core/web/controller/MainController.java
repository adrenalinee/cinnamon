package org.cinnamon.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * created at 2016. 12. 26.
 * @author shindongseong
 */
@Controller
public class MainController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	String main() {
		return "index";
	}
	
	
	@RequestMapping("/core/components/**")
	void components() {
		logger.info("start");
	}
}
