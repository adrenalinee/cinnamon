package org.cinnamon.core.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.UserAuthority;
import org.cinnamon.core.domain.MenuAuthority;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.exception.InvalidEntityException;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.MenuAuthorityRepository;
import org.cinnamon.core.repository.UserAuthorityRepository;
import org.cinnamon.core.repository.SiteRepository;
import org.cinnamon.core.vo.SiteMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
@Service
public class MenuService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SiteRepository siteRepository;
	
	@Autowired
	MenuGroupRepository menuGroupRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	UserAuthorityRepository permissionRepository;
	
	@Autowired
	MenuAuthorityRepository permissionMenuRepository;
	
	
	/**
	 * 
	 * @param menuGroupId
	 * @param menu
	 */
	@Transactional
	public void save(Long menuGroupId, Menu menu) {
		logger.info("start");
		
		MenuGroup menuGroup = menuGroupRepository.findOne(menuGroupId);
		if (menuGroup == null) {
			throw new InvalidEntityException("menuGroup이 없습니다. menuGroupId: " + menuGroupId);
		}
		
		menuRepository.save(menu);
		
		menuGroup.getMenus().add(menu);
		
		//권한 메뉴 추가 - 모든 권한에 추가 하는 메뉴를 넣어 준다.
		List<UserAuthority> permissions = permissionRepository.findAll();
		permissions.stream().forEach(permission -> {
			MenuAuthority permissionMenu = new MenuAuthority();
			permissionMenu.setMenu(menu);
			permissionMenu.setAuthority(permission);
			
			permissionMenuRepository.save(permissionMenu);
		});
	}
	
	
	/**
	 * 
	 * @param menuGroupId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Menu> getMenus(Long menuGroupId) {
		logger.info("start");
		
		return menuGroupRepository.findOne(menuGroupId).getMenus();
	}
	
	
	/**
	 * 
	 * @param site
	 * @param dimension
	 * @param position
	 * @param authority
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Menu> getSitePermisionMenus(String site, String dimension, MenuPosition position, String authority) {
		logger.info("start");
		
		return menuRepository.getSitePermisionMenus(site, dimension, position, authority);
	}
	
	
	@Transactional(readOnly=true)
	public List<Menu> getSitePermisionMenus(String site, String dimension, MenuPosition position, Collection<? extends GrantedAuthority> grantedAuthorities) {
		logger.info("start");
		
//		return menuRepository.getSitePermisionMenus(site, dimension, position, authority);
		return null;
	}
	
	
	/**
	 * 
	 * @param requestUri
	 * @param grantedAuthorities
	 * @param siteId
	 * @param dimension
	 * @return
	 */
	@Transactional(readOnly=true)
	public SiteMenu getSiteMenu(String requestUri, Collection<? extends GrantedAuthority> grantedAuthorities, String siteId, String dimension) {
		logger.info("start");
		
//		String requestUri = request.getRequestURI();
		
		MenuGroup menuGroup = menuGroupRepository.getSiteMenuPosition(siteId, dimension);
		
		SiteMenu siteMenu = new SiteMenu();
		siteMenu.setName(menuGroup.getName());
		
		
		List<String> authorities = new LinkedList<String>();
		grantedAuthorities.forEach(ga -> authorities.add(ga.getAuthority()));
		List<Menu> menus = menuRepository.getSitePermisionMenus(siteId, dimension, MenuPosition.sidebar, authorities);
		siteMenu.setSidebars(menus);
		for (Menu menu: menus) {
			if (menu.getUri() == null) {
				List<Menu> childs = menu.getChilds();
				for (Menu child: childs) {
					if (requestUri.startsWith(child.getUri())) {
						siteMenu.getActives().add(menu);
					}
				}
				
				break;
			}
			
			if (requestUri.startsWith(menu.getUri())) {
				siteMenu.getActives().add(menu);
				break;
			}
		}
		
		
//		for (GrantedAuthority ga: grantedAuthorities) {
//			String authority = ga.getAuthority();
//			
//			List<Menu> menus = menuRepository.getSitePermisionMenus(site, dimension, MenuPosition.sidebar, authority);
//			siteMenu.getSidebars().addAll(menus);
//			
//			for (Menu menu: menus) {
//				if (menu.getUri() == null) {
//					List<Menu> childs = menu.getChilds();
//					for (Menu child: childs) {
//						if (requestUri.startsWith(child.getUri())) {
//							siteMenu.getActives().add(menu);
//						}
//					}
//					
//					break;
//				}
//				
//				if (requestUri.startsWith(menu.getUri())) {
//					siteMenu.getActives().add(menu);
//					break;
//				}
//			}
			
			List<Menu> headerRightMenus = menuRepository.getSitePermisionMenus(siteId, dimension, MenuPosition.headerRight, authorities);
			siteMenu.getHeaderRights().addAll(headerRightMenus);
			
			List<Menu> headerLeftMenus = menuRepository.getSitePermisionMenus(siteId, dimension, MenuPosition.headerLeft, authorities);
			siteMenu.getHeaderLefts().addAll(headerLeftMenus);
//		}
		
		return siteMenu;
	}
}
