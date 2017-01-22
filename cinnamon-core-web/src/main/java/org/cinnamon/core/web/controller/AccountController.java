package org.cinnamon.core.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.cinnamon.core.config.SystemConfigureService;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 신동성
 * @since 2016. 4. 11.
 */
@Controller("cinnamon.accountController")
public class AccountController /*extends BasePageController*/ {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SystemConfigureService systemConfigurerManager;
	
	@Autowired
	private SiteService siteService;
	
	/**
	 * 서버 초기화 되어 있는지 여부
	 */
	private boolean isInitialize;
	
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
	
	@ModelAttribute("site")
	Site site(HttpServletRequest request) {
		logger.info("start");
		
		if (!isInitialize) {
			isInitialize = systemConfigurerManager.isInitialized();
		}
		
		Site site = null;
		System.out.println(request.getRequestURL());
		if (!isInitialize) {
			//기본정보 전달
			site = new Site();
			site.setLabel("Cinnamon");
		} else {
			site = siteService.getDefault();
		}
		
		return site;
	}
}
