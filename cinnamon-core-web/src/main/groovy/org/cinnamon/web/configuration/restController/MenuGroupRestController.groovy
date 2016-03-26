package org.cinnamon.web.configuration.restController

import org.cinnamon.core.domain.Menu
import org.cinnamon.core.domain.MenuGroup
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.service.MenuGroupService
import org.cinnamon.core.service.MenuService;
import org.cinnamon.core.vo.resource.MenuGroupResource
import org.cinnamon.core.vo.resource.MenuResource
import org.dozer.Mapper;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 
 * @author shindongseong
 * @since 2015. 10. 30.
 */
@RestController
@RequestMapping("/rest/configuration/menuGroups")
class MenuGroupRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	MenuGroupService menuGroupService
	
	@Autowired
	MenuService menuService
	
	@Autowired
	Mapper beanMapper
	
	
	@RequestMapping(value="{dimention}", method=RequestMethod.GET)
	MenuGroupResource getMenuGroup(
		@PathVariable String dimention,
		@AuthenticationPrincipal UserDetails userDetails) {
		logger.info("start")
		
		MenuGroup menuGroup = menuGroupService.getByDimension(dimention)
	}
	
	
	
	
//	@RequestMapping(value="/{dimension}/current-menus", method=RequestMethod.GET)
//	MenuGroupResource getMenus(
//		@PathVariable String dimension,
//		@AuthenticationPrincipal UserDetails userDetails) {
//		
//		logger.info("start")
//		
//		List<String> authorities = new LinkedList<>()
//		userDetails.getAuthorities().each({ga ->
//			authorities.add(((GrantedAuthority) ga).getAuthority())
//		})
//		
//		MenuGroup menuGroup = menuGroupService.getByDimension(dimension)
//		
//		MenuGroupResource menuGroupResource = beanMapper.map(menuGroup, MenuGroupResource)
//		menuGroup.menus.each({menu ->
//			if (MenuPosition.headerRight.equals(menu.getPosition())) {
//				menuGroupResource.addHeaderRight(beanMapper.map(menu, MenuResource.class))
//			} else if (MenuPosition.headerLeft.equals(menu.getPosition())) {
//				menuGroupResource.addHeaderLeft(beanMapper.map(menu, MenuResource.class))
//			} else {
//				menuGroupResource.addSidebar(beanMapper.map(menu, MenuResource.class))
//			}
//		})
//		
//		return menuGroupResource
//	}
	
	@RequestMapping(value="/{dimension}/current-menus", method=RequestMethod.GET)
	MenuGroupResource getCurrentMenus(
		@PathVariable String dimension,
		@AuthenticationPrincipal UserDetails userDetails) {
		logger.info("start")
		
		List<String> authorities = new LinkedList<>()
		userDetails.getAuthorities().each({ga ->
			authorities.add(((GrantedAuthority) ga).getAuthority())
		})
		
		MenuGroup menuGroup = menuGroupService.getByDimension(dimension)
		MenuGroupResource menuGroupResource = beanMapper.map(menuGroup, MenuGroupResource)
		
		
		List<MenuResource> menuResources = new LinkedList<>()
		menuService.getList(dimension, MenuPosition.sidebar, authorities).each({menu ->
			menuResources.add(beanMapper.map(menu, MenuResource.class))
		})
		menuGroupResource.setSidebar(menuResources)
		
		menuResources = new LinkedList<>()
		menuService.getList(dimension, MenuPosition.headerRight, authorities).each({menu ->
			menuResources.add(BeanUtils.copyProperties(menu, MenuResource.class))
		})
		menuGroupResource.setHeaderRight(menuResources)
		
		menuResources = new LinkedList<>()
		menuService.getList(dimension, MenuPosition.headerLeft, authorities).each({menu ->
			menuResources.add(beanMapper.map(menu, MenuResource.class))
		})
		menuGroupResource.setHeaderLeft(menuResources)
		
		
		return menuGroupResource
	}
}
