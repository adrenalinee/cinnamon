package org.cinnamon.web.configuration.controller;

import javax.servlet.http.HttpServletRequest;

import org.cinnamon.core.config.SystemConfigureService;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations=Controller.class)
public class CommonControllerAdvice {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SystemConfigureService systemConfigurerManager;
	
	@Autowired
	SiteService siteService;
	
	/**
	 * 서버 초기화 되어 있는지 여부
	 */
	boolean isInitialize;
	
	
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
