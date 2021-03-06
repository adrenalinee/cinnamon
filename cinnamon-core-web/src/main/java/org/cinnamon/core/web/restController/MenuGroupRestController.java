package org.cinnamon.core.web.restController;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.service.MenuGroupService;
import org.cinnamon.core.vo.MenuGroupVo;
import org.cinnamon.core.vo.search.MenuGroupSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author 신동성
 * @since 2016. 3. 2.
 */
@RestController
@RequestMapping("/rest/configuration/menuGroups")
public class MenuGroupRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuGroupService menuGroupService;
	
	/**
	 * 
	 * @param menuGroupVo
	 * @param siteId
	 * @param builder
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postMenuGroups(
		@RequestBody @Valid MenuGroupVo menuGroupVo,
		@RequestParam String siteId,
		UriComponentsBuilder builder) {
		logger.info("start");
		
		MenuGroup menuGroup = menuGroupService.save(siteId, menuGroupVo);
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				MenuGroupRestController.class,
				"getMenuGroup",
				menuGroup.getMenuGroupId())
			.build()
			.toUri();
		
		return ResponseEntity
			.created(location)
			.build();
	}
	
	/**
	 * 
	 * @param menuGroupSearch
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<MenuGroup> getMenuGroups(MenuGroupSearch menuGroupSearch, Pageable pageable) {
		logger.info("start");
		
		return menuGroupService.getList(menuGroupSearch, pageable);
	}
	
	/**
	 * 
	 * @param menuGroupId
	 * @return
	 */
	@RequestMapping(value="{menuGroupId}", method=RequestMethod.GET)
	MenuGroup getMenuGroup(@PathVariable Long menuGroupId) {
		logger.info("start");
		
		return menuGroupService.get(menuGroupId);
	}
	
	/**
	 * 
	 * @param menuGroupId
	 * @param menuGroupVo
	 */
	@RequestMapping(value="{menuGroupId}", method=RequestMethod.PUT)
	void putMenuGroup(@PathVariable Long menuGroupId, @RequestBody @Valid MenuGroupVo menuGroupVo) {
		logger.info("start");
		
		menuGroupService.merge(menuGroupId, menuGroupVo);
	}
	
	/**
	 * 메뉴 그룹 가져오기
	 */
	@RequestMapping(value="/site/{siteId}", method=RequestMethod.GET)
	List<MenuGroup> menuGroups(@PathVariable String siteId) {
		logger.info("start");
		return menuGroupService.getSiteMenuGroups(siteId);
	}

	/**
	 * 사이트 리스트 가져오기
	 * @author 정명성
	 * create date : 2016. 3. 21.
	 * @param menuGroupId
	 * @return
	 */
	@RequestMapping(value="{menuGroupId}/site", method=RequestMethod.GET)
	Site getSiteOfMenuGroup(@PathVariable Long menuGroupId) {
		logger.info("start");
		return menuGroupService.getSiteOfMenuGroup(menuGroupId);
	}
	
	/**
	 * 메뉴 그룹 사이트 추가
	 * @author 정명성
	 * create date : 2016. 3. 21.
	 * @param siteId
	 * @param menuGroupId
	 * @return
	 */
	@RequestMapping(value="{menuGroupId}/site/{siteId}", method=RequestMethod.PUT)
	void putMenuGroupOfSite(@PathVariable Long menuGroupId, @PathVariable String siteId) {
		logger.info("start");
		menuGroupService.putSiteOfMenuGroup(menuGroupId, siteId);
	}
	
	/**
	 * 
	 * @param menuGroupId
	 * @return
	 */
	@RequestMapping(value="{menuGroupId}/deleteable", method=RequestMethod.GET)
	Boolean getDeleteable(@PathVariable Long menuGroupId) {
		logger.info("start");
		
		return menuGroupService.isDeleteable(menuGroupId);
	}
	
	/**
	 * 
	 * @param menuGroupId
	 */
	@RequestMapping(value="{menuGroupId}", method=RequestMethod.DELETE)
	void deleteSite(@PathVariable Long menuGroupId) {
		logger.info("start");
		
		menuGroupService.delete(menuGroupId);
	}
}
