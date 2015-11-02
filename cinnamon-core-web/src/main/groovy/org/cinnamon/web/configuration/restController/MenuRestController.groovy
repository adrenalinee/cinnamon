package org.cinnamon.web.configuration.restController

import org.cinnamon.core.domain.Menu
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.service.MenuService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
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
@RequestMapping("/rest/configuration/menus")
class MenuRestController /*extends BaseRestController<Menu, MenuRepository>*/ {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	MenuService menuService
	
	
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
	
	
}
