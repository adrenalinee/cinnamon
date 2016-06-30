package org.cinnamon.core.service;

import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.repository.UserAuthorityRepository;
import org.cinnamon.core.repository.UserBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author 신동성
 * @since 2016. 5. 6.
 */
@Service
public class SessionService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	UserBaseRepository<UserBase> userRepository;
	
	@Autowired
	UserAuthorityRepository permissionRepository;
	
	/**
	 * 기본 첫 페이지 경로를 전달한다.
	 * @return
	 */
//	@Transactional
//	public String getDefaultFirstPage() {
//		logger.info("start");
//		Site site = siteService.getDefault();
//		
//		if (site != null) {
//			MenuGroup menuGroup = site.getDefaultMenuGroup();
//			if (menuGroup != null) {
//				return menuGroup.getDefaultPage();
//			} else {
//				logger.warn("기본 메뉴 모음이 설정되어 있지 않습니다. siteId: {}", site.getSiteId());
//			}
//		}
//		
//		//초기 페이지가 등록되어 있지 않음
//		return null;
//	}
	
	
	@Transactional
	public String getFirstPage(Authentication authentication) {
		logger.info("start");
		UserBase user = userRepository.findOne(authentication.getName());
		Permission defaultPermission = user.getDefaultPermission();
		if (defaultPermission == null) {
			List<String> authorities = new LinkedList<>();
			authentication.getAuthorities().forEach(ga -> {
				System.out.println(ga.getAuthority());
				authorities.add(ga.getAuthority());
			});
			
			//사용자가 별도로 기본 권한을 설정하지 않았을 경우 처음 검색되는 권한을 기본권한으로 처리함
			defaultPermission = permissionRepository.findFirst1ByAuthorityIn(authorities);
		}
		
		Menu menu = defaultPermission.getDefaultMenu();
		if (menu == null) {
			return null;
		}
		
		System.out.println("getFirstPage: " + menu.getUri());
		
		return menu.getUri();
	}
}
