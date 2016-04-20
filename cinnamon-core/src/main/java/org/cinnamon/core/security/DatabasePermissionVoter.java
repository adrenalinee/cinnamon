package org.cinnamon.core.security;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.domain.PermissionMenuDetail;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.core.repository.MenuAuthorityRepository;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.UserAuthorityRepository;
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
	UserAuthorityRepository permissionRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	MenuGroupRepository menuGroupRepository;
	
	@Autowired
	UserGroupRepository adminGroupRepository;
	
	@Autowired
	MenuAuthorityRepository permissionMenuRepository;
	
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
					
					
					System.out.println(requestUrl);
					
					String dimension = requestUrl;
					if (requestUrl.startsWith("/")) {
						dimension = requestUrl.substring(1);
					}
					if (dimension.endsWith("/")) {
						dimension = dimension.substring(0, dimension.length() - 1);
					}
					
					MenuGroup menuGroup = menuGroupRepository.findByDimension(dimension);
					if (menuGroup != null) {
						return ACCESS_GRANTED;
					}
					
//					System.out.println(menu.getUri());
					if (requestUrl.startsWith(menu.getUri())) {
						//TODO 하위 권한 체크
						
						GrantedAuthority grantedAuthority = (GrantedAuthority) ga;
						//TODO 최적화 필요
						Permission userAauthority = permissionRepository.findByAuthority(grantedAuthority.getAuthority());
						
						PermissionMenu permissionMenu = permissionMenuRepository.findByPermissionAndMenu(userAauthority, menu);
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

						if (grantedAuthority != null) {
							PermissionMenuDetail detail = permissionMenu.getDetails().get(subUri);
							
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
		
	}
	
	
//	public static void main(String[] args) {
//		
//		
//		
//		String requestUrl = "/users/me";
//		Menu menu = new Menu();
//		menu.setUri("/users");
//		
//		String menuUri = menu.getUri() + "/";
//		String subUri = requestUrl.substring(menuUri.length());
//		
//		System.out.println(requestUrl.startsWith(menu.getUri() + "/"));
//		
//		
//		
//	}
}
