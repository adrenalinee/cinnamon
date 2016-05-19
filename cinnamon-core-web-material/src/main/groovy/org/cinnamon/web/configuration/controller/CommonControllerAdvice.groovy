package org.cinnamon.web.configuration.controller

import javax.servlet.http.HttpServletRequest

import org.cinnamon.core.config.SystemConfigureService
import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup
import org.cinnamon.core.domain.Site
import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.domain.enumeration.MenuPosition
import org.cinnamon.core.service.MenuGroupService
import org.cinnamon.core.service.MenuService
import org.cinnamon.core.service.SiteService
import org.cinnamon.core.service.UserBaseService
import org.cinnamon.core.vo.resource.CurrentMenus
import org.cinnamon.core.vo.resource.MenuResource
import org.dozer.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

/**
 * 
 * @author 신동성
 * @since 2016. 5. 16.
 */
@ControllerAdvice(assignableTypes=BasePageController.class)
class CommonControllerAdvice {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	SystemConfigureService systemConfigurerManager
	
	@Autowired
	SiteService siteService
	
	@Autowired
	MenuService menuService
	
	@Autowired
	MenuGroupService menuGroupService
	
	@Autowired
	UserBaseService<UserBase> userBaseService
	
	@Autowired
	Mapper beanMapper
	
	/**
	 * 서버 초기화 되어 있는지 여부
	 */
	boolean isInitialize
	
	
	@ModelAttribute("site")
	Site site(HttpServletRequest request) {
		logger.info("start")
		
		if (!isInitialize) {
			isInitialize = systemConfigurerManager.isInitialized()
		}
		
		Site site = null;
		System.out.println(request.getRequestURL())
		if (!isInitialize) {
			//기본정보 전달
			site = new Site()
			site.setLabel("Cinnamon")
		} else {
			site = siteService.getDefault()
		}
		
		return site
	}
	
	
	@ModelAttribute("currentMenus")
	CurrentMenus currentMenus(
		HttpServletRequest request,
		@AuthenticationPrincipal UserDetails userDetails) {
		logger.info("start")
		
		if (userDetails == null) {
			return null;
		}
		
		String uri = request.getRequestURI();
		String dimension = getDimension(uri);
		
		List<String> authorities = new LinkedList<>()
		userDetails.getAuthorities().each({ga ->
			authorities.add(((GrantedAuthority) ga).getAuthority())
		})
		
		MenuGroup menuGroup = menuGroupService.getByDimension(dimension)
		if (menuGroup == null) {
			//해당 dimension이 없으면 메인 경로 '/'에 해당하는 메뉴모음을 전달한다.
			menuGroup = siteService.getDefault().getDefaultMenuGroup()
			if (menuGroup != null) {
				dimension = menuGroup.getDimension()
			}
		}
		
		
		CurrentMenus currentMenus = beanMapper.map(menuGroup, CurrentMenus.class)
		
		List<Menu> actives = new LinkedList<>()
		List<Menu> menus = new LinkedList<>()
		menuService.getList(dimension, MenuPosition.sidebar, authorities).each({menu ->
			menus.add(menu)
			
			//TODO 활성화된 메뉴인지 확인
			if (menu.getUri() != null) {
//				println menu.getUri()
				if (uri.indexOf(menu.getUri()) > -1) {
					actives.add(menu)
					
					//1뎁스가 활성화 되어 있을 경우 2뎁스에서 활성화된 메뉴도 
					menu.getChilds().forEach({child ->
						println child.getUri()
						if (child.getUri() != null) {
							if (uri.indexOf(child.getUri()) > -1) {
								actives.add(child)
							}
						}
					})
				}
			}
		})
		currentMenus.setActives(actives)
		currentMenus.setSidebar(menus)
		
		menus = new LinkedList<>()
		menuService.getList(dimension, MenuPosition.headerRight, authorities).each({menu ->
			menus.add(beanMapper.map(menu, MenuResource.class))
		})
		currentMenus.setHeaderRight(menus)
		
		menus = new LinkedList<>()
		menuService.getList(dimension, MenuPosition.headerLeft, authorities).each({menu ->
			menus.add(beanMapper.map(menu, MenuResource.class))
		})
		currentMenus.setHeaderLeft(menus)
		
		return currentMenus
	}
	
	@ModelAttribute("me")
	UserBase getMe(@AuthenticationPrincipal UserDetails userDetails) {
		logger.info("start")
		
		if (userDetails == null) {
			return null;
		}
		
		return userBaseService.get(userDetails.getUsername())
	}
	
	
	private String getDimension(String uri) {
		String dimension = uri
		int index = dimension.indexOf("/")
		while (index == 0) {
			dimension = dimension.substring(1)
			index = dimension.indexOf("/")
		}
		
		index = dimension.indexOf("/")
		if (index > -1) {
			return dimension.substring(0, index)
		} else {
			return dimension
		}
	}
}
