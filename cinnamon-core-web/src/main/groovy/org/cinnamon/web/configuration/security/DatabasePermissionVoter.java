package org.cinnamon.web.configuration.security;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuAuthority;
import org.cinnamon.core.domain.MenuAuthorityDetail;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.RoleMenuRepository;
import org.cinnamon.core.repository.RoleRepository;
import org.cinnamon.core.repository.UserGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

/**
 * 
 * @author 동성
 * @since 2014. 12. 24.
 */
@Component
public class DatabasePermissionVoter implements AccessDecisionVoter<FilterInvocation> {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	RoleRepository permissionRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	UserGroupRepository adminGroupRepository;
	
	@Autowired
	RoleMenuRepository permissionMenuRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private TransactionTemplate transactionTemplate;
	
//	Map<String, Menu> menuMap;
//	
//	
//	
//	@PostConstruct
//	public void initMenuMap() {
//		
//	}
	
	@PostConstruct
	public void postConstruct() {
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	
	@Override
	public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
		logger.info("start");
		
		
		return transactionTemplate.execute(status -> {
			String requestUrl = object.getRequestUrl();
			System.out.println(requestUrl);
			
			Collection<? extends GrantedAuthority> gas = authentication.getAuthorities();
			if (gas.isEmpty()) {
				return ACCESS_ABSTAIN;
			}
			
			for (GrantedAuthority ga: authentication.getAuthorities()) {
				String authority = ga.getAuthority();
				System.out.println(authority);
				
				//최고 권한은 시스템의 모든 경로에 접근가능해야 함...
				if (DefinedUserAuthority.systemMaster.name().equals(authority)) {
					return ACCESS_GRANTED;
				}
				
				List<Menu> menus = menuRepository.findByAuthority(authority); //TODO cache에 넣어놓고 읽어야 함. 모든 요청에 db접속하면 서버부담이 커짐
				for (Menu menu: menus) {
					if (StringUtils.isEmpty(menu.getUri())) {
						continue;
					}
					
//					System.out.println("menu uri: " + menu.getUri());
					
	//				System.out.println("requestUrl: " + requestUrl);
//					if (requestUrl.equals(menu.getUri())) {
//						return ACCESS_GRANTED;
//					}
					
					
//					System.out.println(menu.getUri());
					if (requestUrl.startsWith(menu.getUri())) {
						//TODO 하위 권한 체크
						
						RoleGrantedAuthority permissionGrantedAuthority = (RoleGrantedAuthority) ga;
						MenuAuthority permissionMenu = permissionMenuRepository.findByRoleAndMenu(permissionGrantedAuthority, menu);
						if (requestUrl.equals(menu.getUri())) {
							if (permissionMenu.isPermitRoot()) {
								return ACCESS_GRANTED;
							} else {
								return ACCESS_DENIED;
							}
						}
						
						//요청 경로가 하위 경로일 경우
						String menuUri = menu.getUri() + "/";
						String subUri = requestUrl.substring(menuUri.length());
						
						//TODO subUri가 PermissionMenu 하위 권한에 해당하는지 확인
						System.out.println("subUri: " + subUri);

						if (permissionGrantedAuthority != null) {
							MenuAuthorityDetail detail = permissionMenu.getDetails().get(subUri);
							
							if (detail != null) {
								if (!detail.isPermit()) {
									return ACCESS_DENIED;
								} else {
									return ACCESS_GRANTED;
								}
							}
						}
						
						if (permissionMenu.getPermitElse()) {
							return ACCESS_GRANTED;
						}
					}
				}
				
			}
			
			return ACCESS_ABSTAIN;
		});
		
//		System.out.println(4);
//		return ACCESS_GRANTED;
//		return ACCESS_ABSTAIN;
		
		
//		String requestUrl = object.getRequestUrl();
////		System.out.println(requestUrl);
//		
//		List<Menu> menus = getMenus(requestUrl);
//		for (Menu menu: menus) {
////			System.out.println(menu.getUri());
//			
//			String uri = menu.getUri();
//		
//			for (GrantedAuthority ga: authentication.getAuthorities()) {
//				String authority = ga.getAuthority();
//				System.out.println(authority);
//				
//				List<PermissionMenu> permissionMenus = permissionRepository.getPermissionMenus(authority, uri);
//				if (permissionMenus != null) {
//					return ACCESS_GRANTED;
//				}
//			}
//		}
//		
//		return ACCESS_ABSTAIN;
	}
	
	
//	private List<Menu> getMenus(String uri) {
//		List<String> parentPaths = getParentPaths(uri);
//		for (String u: parentPaths) {
//			System.out.println(u);
//		}
//		
//		
//		List<Menu> menus = new LinkedList<Menu>();
//		for (String path: parentPaths) {
//			Menu menu = menuRepository.findByUri(path);
//			if (menu == null) {
//				continue;
//			}
//			
//			menus.add(menu);
//		}
//		
//		return menus;
//	}
	
	
//	private List<String> getParentPaths(String path) {
//		List<String> paths = new LinkedList<String>();
//		
//		int index =  path.lastIndexOf("/");
//		
//		if (index > 0) {
//			paths.add(path);
//		} else {
//			do {
//				paths.add(path);
//				index =  path.lastIndexOf("/");
//			} while (index > 0);
//		}
//		
//		return paths;
//	}
	
	
	public static void main(String[] args) {
		
		
		
		String requestUrl = "/users/me";
		Menu menu = new Menu();
		menu.setUri("/users");
		
		String menuUri = menu.getUri() + "/";
		String subUri = requestUrl.substring(menuUri.length());
		
		System.out.println(requestUrl.startsWith(menu.getUri() + "/"));
		
		
		
	}
}
