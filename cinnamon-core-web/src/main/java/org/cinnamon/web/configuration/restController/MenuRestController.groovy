package org.cinnamon.web.configuration.restController

import javax.validation.Valid
import javax.validation.constraints.NotNull

import org.cinnamon.core.domain.Menu
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.repository.MenuRepository
import org.cinnamon.core.service.MenuService
import org.hibernate.validator.constraints.NotEmpty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
	
	@Autowired
	MenuRepository menuRepository
	
//	@RequestMapping(value="", method=RequestMethod.GET)
//	PagedResources<Menu> menus(@QuerydslPredicate Predicate predicate, Pageable pageable, PagedResourcesAssembler<Menu> assembler) {
//		
//		Page<Menu> menus = menuRepository.findAll(predicate, pageable)
//		
//		assembler.toResource(menus)
//	}
	
//	@RequestMapping(value="", method=RequestMethod.GET)
//	Page<Menu> menus(Pageable pageable) {
//		menuRepository.findAll(pageable)
//	}
	
	
//	@RequestMapping(value="", method=RequestMethod.GET)
//	Page<Menu> menus(@QuerydslPredicate Predicate predicate, Pageable pageable) {
//		menuRepository.findAll(predicate, pageable)
//	}
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<Menu> menus(Pageable pageable) {
	}
	
	@RequestMapping(value="{menuId}", method=RequestMethod.GET)
	Menu get(@PathVariable("menuId") Menu menu) {
		menu
	}
	
	
	
	
	@RequestMapping(value="siteMenus", method=RequestMethod.GET)
	def siteMenu(@Valid SitePermissionMenu sitePermissionMenu) {
		menuService.getSitePermisionMenus(sitePermissionMenu.site,
			sitePermissionMenu.dimension,
			sitePermissionMenu.position,
			sitePermissionMenu.authority)
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
	
	@NotEmpty
	String authority
}