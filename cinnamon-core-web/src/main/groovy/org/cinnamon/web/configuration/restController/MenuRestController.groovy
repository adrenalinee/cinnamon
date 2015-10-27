package org.cinnamon.web.configuration.restController

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid
import javax.validation.constraints.NotNull

import org.cinnamon.core.domain.Menu
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.service.MenuService
import org.cinnamon.core.vo.SiteMenu
import org.hibernate.validator.constraints.NotEmpty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 
 * @author shindongseong
 *
 */
@RestController
@RequestMapping("/rest/configuration/menus")
class MenuRestController /*extends BaseRestController<Menu, MenuRepository>*/ {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	MenuService menuService
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<Menu> menus(Pageable pageable) {
	}
	
	@RequestMapping(value="{menuId}", method=RequestMethod.GET)
	Menu get(@PathVariable("menuId") Menu menu) {
		menu
	}
	
	
	
	
//	@RequestMapping(value="siteMenus", method=RequestMethod.GET)
//	SiteMenu siteMenu(@Valid SitePermissionMenu sitePermissionMenu) {
//		menuService.getSitePermisionMenus(sitePermissionMenu.site,
//			sitePermissionMenu.dimension,
//			sitePermissionMenu.position,
//			sitePermissionMenu.authority)
//	}
	
	@RequestMapping(value="siteMenu", method=RequestMethod.GET)
	SiteMenu siteMenu(@Valid SitePermissionMenu sitePermissionMenu, @AuthenticationPrincipal UserDetails userDetails) {
		
		menuService.getSiteMenu(sitePermissionMenu.site,
			sitePermissionMenu.dimension,
			sitePermissionMenu.position,
			userDetails.getAuthorities())
	}
	
	@RequestMapping(value="siteMenu/{siteId}/{dimension}", method=RequestMethod.GET)
	SiteMenu getSiteMenu(
		@PathVariable String siteId,
		@PathVariable String dimension,
		@AuthenticationPrincipal UserDetails userDetails) {
		
		logger.info("start")
		
		menuService.getSiteMenu(siteId, dimension, userDetails.getAuthorities())
	}
}


/**
 * 
 *
 * @author 신동성
 */
class SitePermissionMenu {
	
	@NotEmpty
	String site
	
	@NotEmpty
	String dimension
	
	@NotNull
	MenuPosition position
	
//	@NotEmpty
//	String authority
}