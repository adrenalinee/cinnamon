package org.cinnamon.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 신동성
 * @since 2016. 4. 11.
 */
@Controller("cinnamon.accountController")
public class AccountController extends BasePageController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/login")
	public String login() {
		logger.info("start");
		return "configuration/login";
	}
	
	@RequestMapping(value="/join")
	public String join() {
		logger.info("start");
		return "configuration/join";
	}
	
	
	
	@RequestMapping(value="/findPassword")
	public String findPassword() {
		logger.info("start");
		return "configuration/findPassword";
	}
}
