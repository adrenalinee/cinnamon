package org.cinnamon.web.configuration.restController

import org.cinnamon.core.domain.Menu
import org.cinnamon.core.domain.MenuGroup
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.service.MenuGroupService
import org.cinnamon.core.service.MenuService
import org.cinnamon.core.vo.resource.MenuGroupResource
import org.cinnamon.core.vo.resource.MenuResource
import org.dozer.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/session")
class SessionRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	MenuService menuService
	
	@Autowired
	MenuGroupService menuGroupService
	
	@Autowired
	Mapper beanMapper
	
	
	@RequestMapping(value="/current-menus/{dimension}", method=RequestMethod.GET)
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
