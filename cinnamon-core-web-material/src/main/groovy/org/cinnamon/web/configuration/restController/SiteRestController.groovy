package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.domain.Menu
import org.cinnamon.core.domain.Site
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.enumeration.Groups
import org.cinnamon.core.service.GroupService
import org.cinnamon.core.service.MenuService;
import org.cinnamon.core.service.SiteService
import org.cinnamon.core.vo.SiteVo
import org.cinnamon.core.vo.search.SiteSearch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

/**
 * 
 * @author 신동성
 */
@RestController
@RequestMapping("/rest/configuration/sites")
class SiteRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	SiteService siteService
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	MenuService menuService
	
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postSites(@RequestBody @Valid SiteVo siteVo, UriComponentsBuilder builder) {
		logger.info("start")
		
		Site site = siteService.save(siteVo)
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				SiteRestController.class,
				"getSite",
				site.getSiteId())
			.build()
			.toUri()
		
		return ResponseEntity
			.created(location)
			.build()
	}
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<Site> getSites(SiteSearch siteSearch, Pageable pageable) {
		logger.info("start")
		
		return siteService.getList(siteSearch, pageable)
	}
	
	
	@RequestMapping(value="{SiteId}", method=RequestMethod.GET)
	Site getSite(@PathVariable String SiteId) {
		logger.info("start")
		
		return siteService.get(SiteId)
	}
	
	
	@RequestMapping(value="{SiteId}", method=RequestMethod.PUT)
	void putSite(@PathVariable String SiteId, @RequestBody @Valid SiteVo siteVo) {
		logger.info("start")
		
		siteService.save(SiteId, siteVo)
	}
	
	
	// "/rest/configuration/sites/{siteId}/menuGroups/{menuGroupId}/menus"
	@RequestMapping(value="{siteId}/menuGroups/{menuGroupId}/menus", method=RequestMethod.GET)
	List<MenuWithPosition> list (@PathVariable String siteId, @PathVariable Long menuGroupId) {
		logger.info("start");
		
		Map<String, String> groupsMap = groupService.childsMap(Groups.menuPositions.name());
		
		List<Menu> allMenus = menuService.getMenus(menuGroupId);
		
		List<MenuWithPosition> separatedMenus = new LinkedList<MenuWithPosition>();
		List<Menu> sidebarMenus = new LinkedList<Menu>();
		List<Menu> headerLeftMenus = new LinkedList<Menu>();
		List<Menu> headerRightMenus = new LinkedList<Menu>();
		
		for (Menu menu: allMenus) {
			if (MenuPosition.sidebar.equals(menu.getPosition())) {
				sidebarMenus.add(menu);
			}
			
			if (MenuPosition.headerLeft.equals(menu.getPosition())) {
				headerLeftMenus.add(menu);
			}
			
			if (MenuPosition.headerRight.equals(menu.getPosition())) {
				headerRightMenus.add(menu);
			}
		}
		
		
		MenuWithPosition menuWithPosition = new MenuWithPosition();
		menuWithPosition.setPosition(groupsMap.get(MenuPosition.sidebar.name()));
		menuWithPosition.setMenus(sidebarMenus);
		separatedMenus.add(menuWithPosition);
		
		menuWithPosition = new MenuWithPosition();
		menuWithPosition.setPosition(groupsMap.get(MenuPosition.headerLeft.name()));
		menuWithPosition.setMenus(headerLeftMenus);
		separatedMenus.add(menuWithPosition);
		
		menuWithPosition = new MenuWithPosition();
		menuWithPosition.setPosition(groupsMap.get(MenuPosition.headerRight.name()));
		menuWithPosition.setMenus(headerRightMenus);
		separatedMenus.add(menuWithPosition);
		
		
		return separatedMenus;
	}
}

/**
 *
 * @author shindongseong
 *
 */
class MenuWithPosition {
	
	String position;
	
	List<Menu> menus;

	String getPosition() {
		return position;
	}

	def setPosition(String position) {
		this.position = position;
	}

	List<Menu> getMenus() {
		return menus;
	}

	def setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}