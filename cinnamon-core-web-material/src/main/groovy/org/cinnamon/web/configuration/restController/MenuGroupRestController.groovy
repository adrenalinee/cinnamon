package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.domain.MenuGroup
import org.cinnamon.core.service.MenuGroupService
import org.cinnamon.core.vo.MenuGroupVo
import org.cinnamon.core.vo.search.MenuGroupSearch
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
 * @since 2016. 3. 2.
 */
@RestController
@RequestMapping("/rest/configuration/menuGroups")
class MenuGroupRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	MenuGroupService menuGroupService
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postMenuGroups(@RequestBody @Valid MenuGroupVo menuGroupVo, UriComponentsBuilder builder) {
		logger.info("start")
		
		MenuGroup menuGroup = menuGroupService.save(menuGroupVo)
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				MenuGroupRestController.class,
				"getMenuGroup",
				menuGroup.getMenuGroupId())
			.build()
			.toUri()
		
		return ResponseEntity
			.created(location)
			.build()
	}
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<MenuGroup> getMenuGroups(MenuGroupSearch menuGroupSearch, Pageable pageable) {
		logger.info("start")
		
		return menuGroupService.getList(menuGroupSearch, pageable)
	}
	
	
	@RequestMapping(value="{menuGroupId}", method=RequestMethod.GET)
	MenuGroup getMenuGroup(@PathVariable Long menuGroupId) {
		logger.info("start")
		
		return menuGroupService.get(menuGroupId)
	}
	
	
	@RequestMapping(value="{menuGroupId}", method=RequestMethod.PUT)
	void putMenuGroup(@PathVariable Long menuGroupId, @RequestBody @Valid MenuGroupVo menuGroupVo) {
		logger.info("start")
		
		menuGroupService.save(menuGroupId, menuGroupVo)
	}
	
	/**
	 * 메뉴 그룹 가져오기
	 */
	@RequestMapping(value="/site/{siteId}", method=RequestMethod.GET)
	List<MenuGroup> menuGroups(@PathVariable String siteId) {
		logger.info("start")
		return menuGroupService.getSiteMenuGroups(siteId);
	}
}
