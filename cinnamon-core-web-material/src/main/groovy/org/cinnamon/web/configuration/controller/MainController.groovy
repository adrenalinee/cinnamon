package org.cinnamon.web.configuration.controller

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.service.SiteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 *
 * created date: 2015. 9. 24.
 * @author 신동성
 */
@Controller
class MainController {
	
	@Autowired
	SiteService siteService
	
	
	String defaultPage;
	
	
	@RequestMapping("/")
	String index() {
//		"redirect:/configuration"
		
		if (defaultPage != null) {
			return defaultPage
		}
		
		
		Site site = siteService.getDefault();
		println site
		if (site != null) {
			MenuGroup menuGroup = site.getDefaultMenuGroup()
			println menuGroup
			if (menuGroup != null) {
				println menuGroup.getDefaultPage()
				defaultPage = "redirect:" + menuGroup.getDefaultPage()
				return defaultPage
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
