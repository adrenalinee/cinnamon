package org.cinnamon.core.web.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.security.UserDetailImpl;
import org.cinnamon.core.service.MenuGroupService;
import org.cinnamon.core.service.MenuService;
import org.cinnamon.core.service.SiteService;
import org.cinnamon.core.vo.resource.CurrentMenus;
import org.cinnamon.core.vo.resource.MenuResource;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

/**
 * 
 * created at: 2017. 1. 22.
 * @author dsshin
 */
@RestController
@RequestMapping("/rest/layout")
public class LayoutRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MenuGroupService menuGroupService;
	
	@Autowired
	private Mapper beanMapper;
	
	@RequestMapping(value="currentMenus", method=RequestMethod.GET)
	CurrentMenus currentMenus(
		HttpServletRequest request,
		@AuthenticationPrincipal UserDetailImpl userDetails) {
		logger.info("start");
		
		if (userDetails == null) {
			return null;
		}
		
		final String uri = request.getRequestURI();
		String dimension = getDimension(uri);
		
		List<String> authorities = Lists.newArrayList();
		userDetails.getAuthorities().forEach(ga -> {
			authorities.add(((GrantedAuthority) ga).getAuthority());
		});
		
		MenuGroup menuGroup = menuGroupService.getByDimension(dimension);
		if (menuGroup == null) {
			//해당 dimension이 없으면 메인 경로 '/'에 해당하는 메뉴모음을 전달한다.
			menuGroup = siteService.getDefault().getDefaultMenuGroup();
			if (menuGroup != null) {
				dimension = menuGroup.getDimension();
			}
		}
		
		
		CurrentMenus currentMenus = beanMapper.map(menuGroup, CurrentMenus.class);
		
		final List<MenuResource> actives = Lists.newArrayList();
		final List<MenuResource> sidebarMenus = Lists.newArrayList();
		
		int firstDepthIndex = 0;
		int secondDepthIndex = 0;
		
//			menuService.getList(dimension, MenuPosition.sidebar, authorities).forEach(menu -> {
		for (Menu menu: menuService.getList(dimension, MenuPosition.sidebar, authorities)) {
			sidebarMenus.add(beanMapper.map(menu, MenuResource.class));
			
			//TODO 활성화된 메뉴인지 확인
			if (menu.getUri() != null) {
//					println menu.getUri()
				if (uri.indexOf(menu.getUri()) > -1) {
					actives.add(beanMapper.map(menu, MenuResource.class));
					currentMenus.setCurrent1depthIndex(firstDepthIndex);
					
					//1뎁스가 활성화 되어 있을 경우 2뎁스에서 활성화된 메뉴도 
//						menu.getChilds().forEach(child -> {
					for (Menu child: menu.getChilds()) {
//							println child.getUri()
						if (child.getUri() != null) {
							if (uri.indexOf(child.getUri()) > -1) {
								actives.add(beanMapper.map(menu, MenuResource.class));
								currentMenus.setCurrent2depthIndex(secondDepthIndex);
							}
						}
						
						secondDepthIndex++;
					}
				}
			}
			
			firstDepthIndex++;
		}
		currentMenus.setActives(actives);
		currentMenus.setSidebar(sidebarMenus);
		
		final List<MenuResource> headerRightMenus = Lists.newArrayList();
		menuService.getList(dimension, MenuPosition.headerRight, authorities).forEach(menu -> {
			headerRightMenus.add(beanMapper.map(menu, MenuResource.class));
		});
		currentMenus.setHeaderRight(headerRightMenus);
		
		final List<MenuResource> headerLeftmenus = Lists.newArrayList();
		menuService.getList(dimension, MenuPosition.headerLeft, authorities).forEach(menu -> {
			headerLeftmenus.add(beanMapper.map(menu, MenuResource.class));
		});
		currentMenus.setHeaderLeft(headerLeftmenus);
		
		return currentMenus;
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
