package org.cinnamon.core.web.restController;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.service.MenuGroupService;
import org.cinnamon.core.service.MenuService;
import org.cinnamon.core.service.SiteService;
import org.cinnamon.core.service.UserBaseService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@RestController("cinnamon.sessionRestController")
//@RequestMapping("/rest/cinnamon/session")
public class SessionRestController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	MenuGroupService menuGroupService;
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	UserBaseService<UserBase> userBaseService;
	
	
	@Autowired
	Mapper beanMapper;
	
	
//	@RequestMapping(value="/current-menus/{dimension}", method=RequestMethod.GET)
//	MenuGroupResource getCurrentMenus(
//		@PathVariable String dimension,
//		@AuthenticationPrincipal UserDetails userDetails) {
//		logger.info("start")
//		
//		List<String> authorities = new LinkedList<>()
//		userDetails.getAuthorities().each({ga ->
//			authorities.add(((GrantedAuthority) ga).getAuthority())
//		})
//		
//		MenuGroup menuGroup = menuGroupService.getByDimension(dimension)
//		if (menuGroup == null) {
//			//해당 dimension이 없으면 메인 경로 '/'에 해당하는 메뉴모음을 전달한다.
//			menuGroup = siteService.getDefault().getDefaultMenuGroup()
//			if (menuGroup != null) {
//				dimension = menuGroup.getDimension()
//			}
//		}
//		
//		MenuGroupResource menuGroupResource = beanMapper.map(menuGroup, MenuGroupResource)
//		
//		
//		List<MenuResource> menuResources = new LinkedList<>()
//		menuService.getList(dimension, MenuPosition.sidebar, authorities).each({menu ->
//			menuResources.add(beanMapper.map(menu, MenuResource.class))
//		})
//		menuGroupResource.setSidebar(menuResources)
//		
//		menuResources = new LinkedList<>()
//		menuService.getList(dimension, MenuPosition.headerRight, authorities).each({menu ->
//			menuResources.add(beanMapper.map(menu, MenuResource.class))
//		})
//		menuGroupResource.setHeaderRight(menuResources)
//		
//		menuResources = new LinkedList<>()
//		menuService.getList(dimension, MenuPosition.headerLeft, authorities).each({menu ->
//			menuResources.add(beanMapper.map(menu, MenuResource.class))
//		})
//		menuGroupResource.setHeaderLeft(menuResources)
//		
//		
//		return menuGroupResource
//	}
//	
//	
//	@RequestMapping(value="/me", method=RequestMethod.GET)
//	UserBase getMe(@AuthenticationPrincipal UserDetails userDetails) {
//		logger.info("start")
//		
//		return userBaseService.get(userDetails.getUsername());
//	}
}
