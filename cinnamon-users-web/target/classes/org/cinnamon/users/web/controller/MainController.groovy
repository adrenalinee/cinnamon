package org.cinnamon.users.web.controller

import org.cinnamon.core.service.SessionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 *
 * created date: 2015. 9. 24.
 * @author 신동성
 */
@Controller("cinnamon.mainController")
class MainController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
//	@Autowired
//	SiteService siteService
	
	@Autowired
	SessionService sessionService
	
//	@Autowired
//	SecurityContextHolder securityContextHolder
	
	String defaultPage
	
	
	@RequestMapping("/")
	String index(@AuthenticationPrincipal Authentication authentication) {
		logger.info("start")
		
		if (authentication == null) {
			return "redirect:login"
		}
		logger.info(authentication.getName());
		String defaultPage = sessionService.getFirstPage(authentication)
		
		if (StringUtils.isEmpty(defaultPage)) {
			logger.warn("초기 페이지가 지정되지 않았습니다.")
		}
		
		return "redirect:${defaultPage}"
	}
	
	@RequestMapping(value="/partials/**")
	void partials() {
		
	}
	
	@RequestMapping(value="/directives/**")
	void drective() {
		
	}
}
