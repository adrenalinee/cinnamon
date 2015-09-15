package org.cinnamon.core.init.wrapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
@Component
public class ProjectBuilder {
	
	@Autowired
	EntityManager em;
	
	@Autowired
	PermissionRepository permissionRepository;
	
	List<SiteWrapper> siteWrappers = new LinkedList<>();
	
	List<RoleWrapper> roleWrappers = new LinkedList<>();
	
	List<GroupWrapper> groupWrappers = new LinkedList<>();
	
	
	public ProjectBuilder addSite(SiteWrapper siteWrapper) {
		siteWrappers.add(siteWrapper);
		return this;
	}
	
	public ProjectBuilder addRoles(RoleWrapper... roleWrappers) {
		this.roleWrappers.addAll(Arrays.asList(roleWrappers));
		return this;
	}
	
	public static RoleWrapper role(String name, Object authority) {
		return new RoleWrapper(name, authority.toString());
	}
	
	public static SiteWrapper site(String name, String siteId) {
		return new SiteWrapper(name, siteId);
	}
	
	public static MenuGroupWrapper menuGroup(String name, String dimension) {
		return new MenuGroupWrapper(name, dimension);
	}
	
//	public static MenuWrapper menu(String name, MenuPosition position) {
//		return new MenuWrapper(name, position);
//	}
	
	public static MenuWrapper menu(String name) {
		return new MenuWrapper(name);
	}
	
	
	
	public void print() {
		siteWrappers.forEach(sw -> {
			Site site = sw.site;
			System.out.println("site: " + site.getName());
			
			sw.menuGroupWrappers.forEach(mgw -> {
				MenuGroup menuGroup = mgw.menuGroup;
				System.out.println("\tmenuGroup: " + menuGroup.getName());
				
				mgw.menuWrappers.forEach(mw -> {
					Menu menu = mw.menu;
					System.out.println("\t\tmenu: " + menu.getName());
					
					mw.grantedAuthorities.forEach(grantedAuthority -> {
						System.out.println("\t\t\tgrantedAuthority: " + grantedAuthority);
					});
					
					mw.childMenuWrappers.forEach(cmw -> {
						Menu child = cmw.menu;
						System.out.println("\t\t\tchildMenu: " + child.getName());
						
						mw.grantedAuthorities.forEach(grantedAuthority -> {
							System.out.println("\t\t\t\tgrantedAuthority: " + grantedAuthority);
						});
					});
				});
			});
		});
	}
	
	public void build() {
		buildRoles();
		buildSites();
	}
	
	private void buildRoles() {
		roleWrappers.forEach(roleWrapper -> {
			em.persist(roleWrapper.role);
		});
	}
	
	private void buildSites() {
		siteWrappers.forEach(siteWrapper -> {
			Site site = siteWrapper.site;
			em.persist(site);
			
			siteWrapper.menuGroupWrappers.forEach(menuGroupWrapper -> {
				MenuGroup menuGroup = menuGroupWrapper.menuGroup;
				menuGroup.setSite(site);
				em.persist(menuGroup);
				
				menuGroupWrapper.menuWrappers.forEach(menuWrapper -> {
					buildMenus(menuGroup, menuWrapper);
				});
			});
		});
	}
	
	private void buildMenus(MenuGroup menuGroup, MenuWrapper menuWrapper) {
		Menu menu = menuWrapper.menu;
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		menuWrapper.childMenuWrappers.forEach(childMenuWrapper -> {
			Menu childMenu = childMenuWrapper.menu;
			childMenu.setParent(menu);
			em.persist(childMenu);
			
			buildMenus(menuGroup, childMenuWrapper);
			buildMenuRoles(menuWrapper);
		});
	}
	
	private void buildMenuRoles(MenuWrapper menuWrapper) {
		Menu menu = menuWrapper.menu;
		
		menuWrapper.grantedAuthorities.forEach(authority -> {
			Permission permission = permissionRepository.findByAuthority(authority);
			if (permission == null) {
				//정의 되지 않은 역할임
				throw new RuntimeException("정의 되지 않은 권한입니다. authority: " + authority);
			}
			
			PermissionMenu permissionMenu = new PermissionMenu();
			permissionMenu.setMenu(menu);
			permissionMenu.setPermission(permission);
			em.persist(permissionMenu);
		});
	}
}
