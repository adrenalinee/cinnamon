package org.cinnamon.core.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.cinnamon.core.config.SystemConfigureService;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.security.UserDetailImpl;
import org.cinnamon.core.service.MenuGroupService;
import org.cinnamon.core.service.MenuService;
import org.cinnamon.core.service.SiteService;
import org.cinnamon.core.service.UserBaseService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 
 * @author 신동성
 * @since 2016. 5. 16.
 */
@ControllerAdvice(assignableTypes=BasePageController.class)
public class CommonControllerAdvice {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SystemConfigureService systemConfigurerManager;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MenuGroupService menuGroupService;
	
	@Autowired
	private UserBaseService<UserBase> userBaseService;
	
	@Autowired
	private Mapper beanMapper;
	
	/**
	 * 서버 초기화 되어 있는지 여부
	 */
	private boolean isInitialize;
	
	
	@ModelAttribute("site")
	Site site(HttpServletRequest request) {
		logger.info("start");
		
		if (!isInitialize) {
			isInitialize = systemConfigurerManager.isInitialized();
		}
		
		Site site = null;
		System.out.println(request.getRequestURL());
		if (!isInitialize) {
			//기본정보 전달
			site = new Site();
			site.setLabel("Cinnamon");
		} else {
			site = siteService.getDefault();
		}
		
		return site;
	}
	
	
	
//	@ModelAttribute("currentMenus")
//	CurrentMenus currentMenus(
//		HttpServletRequest request,
////		@AuthenticationPrincipal UserDetails userDetails) {
//		@AuthenticationPrincipal UserDetailImpl userDetails) {
//		logger.info("start");
//		
//		if (userDetails == null) {
//			return null;
//		}
//		
//		final String uri = request.getRequestURI();
//		String dimension = getDimension(uri);
//		
//		List<String> authorities = Lists.newArrayList();
//		userDetails.getAuthorities().forEach(ga -> {
//			authorities.add(((GrantedAuthority) ga).getAuthority());
//		});
//		
//		MenuGroup menuGroup = menuGroupService.getByDimension(dimension);
//		if (menuGroup == null) {
//			//해당 dimension이 없으면 메인 경로 '/'에 해당하는 메뉴모음을 전달한다.
//			menuGroup = siteService.getDefault().getDefaultMenuGroup();
//			if (menuGroup != null) {
//				dimension = menuGroup.getDimension();
//			}
//		}
//		
//		
//		CurrentMenus currentMenus = beanMapper.map(menuGroup, CurrentMenus.class);
//		
//		final List<MenuResource> actives = Lists.newArrayList();
//		final List<MenuResource> sidebarMenus = Lists.newArrayList();
//		
//		int firstDepthIndex = 0;
//		int secondDepthIndex = 0;
//		
////		menuService.getList(dimension, MenuPosition.sidebar, authorities).forEach(menu -> {
//		for (Menu menu: menuService.getList(dimension, MenuPosition.sidebar, authorities)) {
//			sidebarMenus.add(beanMapper.map(menu, MenuResource.class));
//			
//			//TODO 활성화된 메뉴인지 확인
//			if (menu.getUri() != null) {
////				println menu.getUri()
//				if (uri.indexOf(menu.getUri()) > -1) {
//					actives.add(beanMapper.map(menu, MenuResource.class));
//					currentMenus.setCurrent1depthIndex(firstDepthIndex);
//					
//					//1뎁스가 활성화 되어 있을 경우 2뎁스에서 활성화된 메뉴도 
////					menu.getChilds().forEach(child -> {
//					for (Menu child: menu.getChilds()) {
////						println child.getUri()
//						if (child.getUri() != null) {
//							if (uri.indexOf(child.getUri()) > -1) {
//								actives.add(beanMapper.map(menu, MenuResource.class));
//								currentMenus.setCurrent2depthIndex(secondDepthIndex);
//							}
//						}
//						
//						secondDepthIndex++;
//					}
//				}
//			}
//			
//			firstDepthIndex++;
//		}
//		currentMenus.setActives(actives);
//		currentMenus.setSidebar(sidebarMenus);
//		
//		final List<MenuResource> headerRightMenus = Lists.newArrayList();
//		menuService.getList(dimension, MenuPosition.headerRight, authorities).forEach(menu -> {
//			headerRightMenus.add(beanMapper.map(menu, MenuResource.class));
//		});
//		currentMenus.setHeaderRight(headerRightMenus);
//		
//		final List<MenuResource> headerLeftmenus = Lists.newArrayList();
//		menuService.getList(dimension, MenuPosition.headerLeft, authorities).forEach(menu -> {
//			headerLeftmenus.add(beanMapper.map(menu, MenuResource.class));
//		});
//		currentMenus.setHeaderLeft(headerLeftmenus);
//		
//		return currentMenus;
//	}
	
	@ModelAttribute("me")
//	UserBase getMe(@AuthenticationPrincipal UserDetails userDetails) {
	UserBase getMe(@AuthenticationPrincipal UserDetailImpl userDetails) {
		logger.info("start");
		
		if (userDetails == null) {
			return null;
		}
		
		return userBaseService.get(userDetails.getUsername());
	}
	
	/**
	 * 1depth path를 해당 경로의 dimension으로 봄
	 * @param uri
	 * @return
	 */
	private String getDimension(String uri) {
		String dimension = uri;
		int index = dimension.indexOf("/");
		while (index == 0) {
			dimension = dimension.substring(1);
			index = dimension.indexOf("/");
		}
		
		index = dimension.indexOf("/");
		if (index > -1) {
			return dimension.substring(0, index);
		} else {
			return dimension;
		}
	}
}
