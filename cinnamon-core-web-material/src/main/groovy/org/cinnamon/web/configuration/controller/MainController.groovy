package org.cinnamon.web.configuration.controller

import org.cinnamon.core.domain.MenuGroup
import org.cinnamon.core.domain.Site
import org.cinnamon.core.service.SiteService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
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
	
	@Autowired
	SiteService siteService
	
	
	String defaultPage;
	
	
	@RequestMapping("/")
	String index() {
		logger.info("start")
//		"redirect:/configuration"
		
		if (defaultPage != null) {
			return defaultPage
		}
		
		
		Site site = siteService.getDefault();
		println site.getName()
		if (site != null) {
			MenuGroup menuGroup = site.getDefaultMenuGroup()
			println menuGroup
			if (menuGroup != null) {
				println menuGroup.getDefaultPage()
				defaultPage = "redirect:" + menuGroup.getDefaultPage()
				return defaultPage
			} else {
				logger.warn("기본 메뉴 모음이 설정되어 있지 않습니다. siteId: {}", site.getSiteId())
			}
		}
		
		//초기 페이지가 등록되어 있지 않음
		defaultPage = ""
		return defaultPage
	}
	
	@RequestMapping(value="/partials/**")
	void partials() {
		
	}
	
	@RequestMapping(value="/directives/**")
	void drective() {
		
	}
}
