package org.cinnamon.web.configuration.restController

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.service.MenuService
import org.cinnamon.core.vo.SiteMenu
import org.cinnamon.core.vo.resource.DimensionMenusResource
import org.cinnamon.core.vo.resource.MenuResource
import org.dozer.Mapper;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal
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
@RequestMapping("/rest/{dimension}/menus")
class MenuRestController /*extends BaseRestController<Menu, MenuRepository>*/ {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	MenuService menuService
	
	@Autowired
	Mapper beanMapper;
	
	
	@RequestMapping(value="{position}", method=RequestMethod.GET)
	List<Menu> getMenus(
		@PathVariable String dimension,
		@PathVariable MenuPosition position,
		@AuthenticationPrincipal UserDetails userDetails) {
		
		logger.info("start")
		
		List<String> authorities = new LinkedList<>()
		userDetails.getAuthorities().each({ga ->
			authorities.add(((GrantedAuthority) ga).getAuthority())
		})
		
		
		menuService.getList(dimension, position, authorities)
	}
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	DimensionMenusResource getMenus(
		@PathVariable String dimension,
		@AuthenticationPrincipal UserDetails userDetails) {
		
		logger.info("start")
		
		List<String> authorities = new LinkedList<>()
		userDetails.getAuthorities().each({ga ->
			authorities.add(((GrantedAuthority) ga).getAuthority())
		})
		
		List<Menu> menus = menuService.getList(dimension, MenuPosition.sidebar, authorities)
		List<MenuResource> menuResources = new LinkedList<>()
		menus.each({menu ->
			menuResources.add(beanMapper.map(menu, MenuResource.class))
		})
		
		DimensionMenusResource dimensionMenusResource = new DimensionMenusResource()
		dimensionMenusResource.setSidebar(menuResources)
		
		return dimensionMenusResource
	}
}
