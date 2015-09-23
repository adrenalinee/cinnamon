package org.cinnamon.core.config.baseData;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Group;
import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Role;
import org.cinnamon.core.domain.RoleMenu;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
@Component
public class BaseDataBuilder {
	
	@Autowired
	EntityManager em;
	
	@Autowired
	RoleRepository permissionRepository;
	
	List<SiteWrapper> siteWrappers = new LinkedList<>();
	
	List<RoleWrapper> roleWrappers = new LinkedList<>();
	
	List<GroupWrapper> groupWrappers = new LinkedList<>();
	
	
	public BaseDataBuilder addSite(SiteWrapper siteWrapper) {
		siteWrappers.add(siteWrapper);
		return this;
	}
	
	public BaseDataBuilder addRoles(RoleWrapper... roleWrappers) {
		this.roleWrappers.addAll(Arrays.asList(roleWrappers));
		return this;
	}
	
	public BaseDataBuilder addGroups(GroupWrapper... groupWrappers) {
		this.groupWrappers.addAll(Arrays.asList(groupWrappers));
		return this;
	}
	
	public static GroupWrapper group(String name, Object groupId) {
		return new GroupWrapper(name, groupId);
	}
	
	public static RoleWrapper role(String name, Object authority) {
		return new RoleWrapper(name, authority.toString());
	}
	
	public static UserGroupWrapper userGroup(String name) {
		return new UserGroupWrapper(name);
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
		groupWrappers.forEach(gw -> {
			Group group = gw.group;
			System.out.println("group: " + group.getName() + "(" + group.getGroupId() + ")");
			
			gw.childGroupWrappers.forEach(cgw -> {
				Group childGroup = cgw.group;
				System.out.println("\tgroup: " + childGroup.getName() + "(" + childGroup.getGroupId() + ")");
			});
		});
		
		roleWrappers.forEach(rw -> {
			Role role = rw.role;
			System.out.println("role: " + role.getName() + "(" + role.getAuthority() + ")");
		});
		
		
		siteWrappers.forEach(sw -> {
			Site site = sw.site;
			System.out.println("site: " + site.getName() + "(" + site.getSiteId() + ")");
			
			sw.menuGroupWrappers.forEach(mgw -> {
				MenuGroup menuGroup = mgw.menuGroup;
				System.out.println("\tmenuGroup: " + menuGroup.getName());
				
				mgw.menuWrappers.forEach(mw -> {
					Menu menu = mw.menu;
					System.out.println("\t\tmenu: " + menu.getName() + "(" + menu.getUri() + ")");
					
					mw.grantedAuthorities.forEach(grantedAuthority -> {
						System.out.println("\t\t\tgrantedAuthority: " + grantedAuthority);
					});
					
					mw.childMenuWrappers.forEach(cmw -> {
						Menu child = cmw.menu;
						System.out.println("\t\t\tchildMenu: " + child.getName() + "(" + child.getUri() + ")");
						
						mw.grantedAuthorities.forEach(grantedAuthority -> {
							System.out.println("\t\t\t\tgrantedAuthority: " + grantedAuthority);
						});
					});
				});
			});
		});
	}
	
	public void build() {
		buildGroups();
		buildRoles();
		buildSites();
	}
	
	int orders;
	int childOrders;
	
	private void buildGroups() {
		orders = 0;
		groupWrappers.forEach(groupWrapper -> {
			buildGroups(groupWrapper, orders++);
		});
	}
	
	private void buildGroups(GroupWrapper groupWrapper, int orders) {
		Group group = groupWrapper.group;
		group.setOrders(orders);
		em.persist(group);
		
		childOrders = 0;
		groupWrapper.childGroupWrappers.forEach(childGroupWrapper -> {
			buildGroups(childGroupWrapper, childOrders++);
		});
	}
	
	private void buildRoles() {
		roleWrappers.forEach(roleWrapper -> {
			Role role = roleWrapper.role;
			em.persist(role);
			
			roleWrapper.userGroupWrappers.forEach(userGroupWrapper -> {
				UserGroup userGroup = userGroupWrapper.userGroup;
				userGroup.setRole(role);
				em.persist(userGroup);
				
				if (userGroupWrapper.isDefault) {
					role.setDefaultUserGroup(userGroup);
				}
			});
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
			Role permission = permissionRepository.findOne(authority);
			if (permission == null) {
				//정의 되지 않은 역할임
				throw new RuntimeException("정의 되지 않은 권한입니다. authority: " + authority);
			}
			
			RoleMenu permissionMenu = new RoleMenu();
			permissionMenu.setMenu(menu);
			permissionMenu.setRole(permission);
			em.persist(permissionMenu);
		});
	}
}
