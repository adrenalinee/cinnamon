package org.cinnamon.core.init.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.PermissionMenuDetailRepository;
import org.cinnamon.core.repository.PermissionMenuRepository;
import org.cinnamon.core.repository.PermissionRepository;
import org.cinnamon.core.repository.SiteRepository;
import org.cinnamon.core.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * 
 * create date: 2015. 5. 26.
 * @author 신동성
 *
 */
@Component
public class SiteBuilder {
	
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private MenuGroupRepository menuGroupRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Autowired
	private PermissionMenuRepository permissionMenuRepository;
	
	@Autowired
	private PermissionMenuDetailRepository permissionMenuDetailRepository;
	
	private Map<String, SiteWrapper> sites = new LinkedHashMap<String, SiteWrapper>();
	
	private Map<String, RoleWrapper> roles = new LinkedHashMap<String, RoleWrapper>();
	
	
	/**
	 * 사이트 설정
	 * 
	 * @param siteId
	 * @return
	 */
	public SiteWrapper site(String siteId) {
		SiteWrapper site = sites.get(siteId);
		if (site == null) {
			site = new SiteWrapper(siteId);
			sites.put(siteId, site);
		}
		
		return site;
	}
	
	
	/**
	 * 열할 설정
	 * 
	 * @param authority
	 * @return
	 */
	public RoleWrapper role(String authority) {
		RoleWrapper role = roles.get(authority);
		if (role == null) {
			role = new RoleWrapper(this, authority);
			roles.put(authority, role);
		}
		
		return role;
	}
	
	
//	public RoleWrapper role(DefinedUserAuthority authority) {
//		return role(authority.name());
//	}
	
	
	private void clear() {
		sites = null;
		roles = null;
	}
	
	
	public void sitePrint() {
		sites.forEach((siteId, sw) -> {
			Site site = sw.site();
			System.out.print("site: " + siteId + " - ");
			System.out.println(ToStringBuilder.reflectionToString(site));
			
			sw.menuGroups().forEach((dimension, mgw) -> {
				MenuGroup menuGroup = mgw.menuGroup();
				menuGroup.setSite(site);
				System.out.print("\tmenuGroup: " + dimension + " - ");
				System.out.println(ToStringBuilder.reflectionToString(menuGroup));
				
				mgw.menus().forEach((mkey, mw) -> {
					Menu menu = mw.menu();
					menu.setMenuGroup(menuGroup);
					System.out.print("\t\tmenu: " + mkey + " - ");
					System.out.println(ToStringBuilder.reflectionToString(menu));
					
					mw.menuRoles().forEach((authority, mrw) -> {
						PermissionMenu permissionMenu = mrw.permissionMenu();
						System.out.print("\t\tpermissionMenu: " + authority + " - ");
						System.out.println(ToStringBuilder.reflectionToString(permissionMenu));
					});
					
					mw.childMenus().forEach((smkey, mw2) -> {
						Menu secondMenu = mw2.menu();
						secondMenu.setMenuGroup(menuGroup);
						secondMenu.setParent(menu);
						System.out.print("\t\t\tmenu: " + smkey + " - ");
						System.out.println(ToStringBuilder.reflectionToString(secondMenu));
						
						mw2.menuRoles().forEach((authority, mrw2) -> {
							PermissionMenu permissionMenu = mrw2.permissionMenu();
							System.out.print("\t\t\tpermissionMenu: " + authority + " - ");
							System.out.println(ToStringBuilder.reflectionToString(permissionMenu));
						});
					});
					
				});
				
//				mgw.clear();
			});
			
//			sw.clear();
		});
	}
	
	
	public void build() {
//		sitePrint();
		
		
		roles.forEach((authority, rw) -> {
			Permission permission = rw.permission();
			permissionRepository.save(permission);
			
			rw.userGroups().forEach((name, ugw) -> {
				UserGroup userGroup = ugw.userGroup();
				userGroup.setPermission(permission);
				userGroupRepository.save(userGroup);
				
				if (ugw.isDefault()) {
					permission.setDefaultUserGroup(userGroup);
				}
			});
		});
		
		
		
		sites.forEach((siteId, sw) -> {
			Site site = sw.site();
			siteRepository.save(site);
			
			sw.menuGroups().forEach((dimension, mgw) -> {
				MenuGroup menuGroup = mgw.menuGroup();
				menuGroup.setSite(site);
				menuGroupRepository.save(menuGroup);
				
				mgw.menus().forEach((mkey, mw) -> {
//					System.out.println("mkey: " + mkey);
					
					Menu menu = mw.menu();
					menu.setMenuGroup(menuGroup);
					menuRepository.save(menu);
					
//					applyMenuGroupRole(mgw.menuGroupRoles(), menu);
					applyMenuRole(mw.menuRoles(), menu);
					
					mw.childMenus().forEach((smkey, mw2) -> {
						Menu secondMenu = mw2.menu();
						secondMenu.setMenuGroup(menuGroup);
						secondMenu.setParent(menu);
						menuRepository.save(secondMenu);
						
//						applyMenuGroupRole(mgw.menuGroupRoles(), secondMenu);
						applyMenuRole(mw2.menuRoles(), secondMenu);
					});
					
				});
			});
		});
		
		clear();
	}
	
//	private void applyMenuGroupRole(Map<String, MenuGroupRoleWrapper> menuGroupRoles, Menu menu) {
//		menuGroupRoles.forEach((authority, mgrw) -> {
//			PermissionMenu permissionMenu = new PermissionMenu();
//			permissionMenu.setReadPower(mgrw.readPower());
//			permissionMenu.setCreatePower(mgrw.createPower());
//			permissionMenu.setUpdatePower(mgrw.updatePower());
//			permissionMenu.setDeletePower(mgrw.deletePower());
//			
//			applyMenuRole(permissionMenu, menu, authority);
//		});
//	}
	
	private void applyMenuRole(Map<String, MenuRoleWrapper> menuRoles, Menu menu) {
		menuRoles.forEach((authority, mrw) -> {
			PermissionMenu permissionMenu = mrw.permissionMenu();
			applyMenuRole(permissionMenu, menu, authority);
		});
	}
	
	
	
	private void applyMenuRole(PermissionMenu permissionMenu, Menu menu, String authority) {
//		if ("메뉴".equals(menu.getName())) {
//			System.out.println("메뉴: " + menu);
//			System.out.println(ToStringBuilder.reflectionToString(permissionMenu));
//			System.out.println(ToStringBuilder.reflectionToString(menu));
//			System.out.println(authority);
//		}
		
		
		if (!permissionMenu.hasPermission()) {
			return;
		}
		
		Permission permission = permissionRepository.findByAuthority(authority);
		if (permission == null) {
			throw new RuntimeException("메뉴에 권한을 부여하기 전에 역할(role)을 먼저 정의 해야 합니다.");
		}
		
//		System.out.println("applyMenuRole menu: " + menu.getName() + ", permission: " + permission.getAuthority());
		
		permissionMenu.setMenu(menu);
		permissionMenu.setPermission(permission);
		permissionMenuRepository.save(permissionMenu);
		
		permissionMenu.getDetails().forEach((name, detail) -> {
			permissionMenuDetailRepository.save(detail);
		});
	}
}
