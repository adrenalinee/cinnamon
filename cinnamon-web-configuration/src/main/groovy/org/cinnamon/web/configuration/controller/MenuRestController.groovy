package org.cinnamon.web.configuration.controller

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.service.MenuService;
import org.cinnamon.core.vo.SiteMenu
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author shindongseong
 *
 */
@RestController
@RequestMapping("/rest/configuration/menus")
class MenuRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	MenuService menuService
	
	@RequestMapping(value="siteMenus", method=RequestMethod.GET)
	def siteMenu(@Valid SitePermissionMenu sitePermissionMenu) {
		menuService.getSitePermisionMenus(sitePermissionMenu.site,
			sitePermissionMenu.dimension,
			sitePermissionMenu.position,
			sitePermissionMenu.authority)
	}
}

class SitePermissionMenu {
	
	@NotEmpty
	String site
	
	@NotEmpty
	String dimension
	
	@NotNull
	MenuPosition position
	
	@NotEmpty
	String authority
}