package org.cinnamon.core.web.restController;

import java.net.URI;

import javax.validation.Valid;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.service.MenuService;
import org.cinnamon.core.vo.MenuVo;
import org.cinnamon.core.vo.search.MenuSearch;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author 신동성
 * @since 2016. 3. 2.
 */
@RestController
@RequestMapping(value="/rest/configuration/menus")
public class MenuRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 
	 * @param menuVo
	 * @param builder
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postMenus(
		//@PathVariable Long menuGroupId,
		@RequestBody @Valid MenuVo menuVo, UriComponentsBuilder builder) {
		logger.info("start");
		
		Menu menu = menuService.save(menuVo);
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				MenuRestController.class,
				"getMenu",
				menu.getMenuId())
			.build()
			.toUri();
		
		return ResponseEntity
			.created(location)
			.build();
	}
	
	/**
	 * 
	 * @param menuSearch
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<Menu> getMenus(MenuSearch menuSearch, Pageable pageable) {
		logger.info("start");
		
		return menuService.getList(menuSearch, pageable);
	}
	
	/**
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="{menuId}", method=RequestMethod.GET)
	Menu getMenu(@PathVariable Long menuId) {
		logger.info("start");
		
		return menuService.get(menuId);
	}
	
	/**
	 * 
	 * @param menuId
	 * @param menuVo
	 */
	@RequestMapping(value="{menuId}", method=RequestMethod.PUT)
	void putMenu(@PathVariable Long menuId, @RequestBody @Valid MenuVo menuVo) {
		logger.info("start");
		
		menuService.modify(menuId, menuVo);
	}
	
	/**
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="{menuId}/deleteable")
	Boolean deleteable(@PathVariable Long menuId) {
		logger.info("start");
		
		Menu menu = menuService.get(menuId);
		
		if (menu.getChilds().size() > 0) {
			return false;
		}
		
		return true;
	}
	/**
	 * 메뉴 삭제
	 * @author 정명성
	 * create date : 2016. 4. 14.
	 * @param menuId
	 */
	@RequestMapping(value="{menuId}", method=RequestMethod.DELETE)
	void deleteMenu(@PathVariable Long menuId) {
		logger.info("start");
		
		menuService.delete(menuId);
	}
}
