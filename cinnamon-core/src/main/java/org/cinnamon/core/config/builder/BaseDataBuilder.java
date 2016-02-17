package org.cinnamon.core.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Group;
import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.enumeration.DefinedUserAuthority;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
//@Component
public class BaseDataBuilder {
	
//	@Autowired
	EntityManager em;
	
//	@Autowired
//	UserAuthorityRepository userAuthorityRepository;
	
	static List<SiteWrapper> siteWrappers = new LinkedList<>();
	
	static List<RoleWrapper> roleWrappers = new LinkedList<>();
	
	static List<GroupWrapper> groupWrappers = new LinkedList<>();
	
	public BaseDataBuilder(EntityManager em) {
		this.em = em;
	}
	
	
	public BaseDataBuilder addSite(SiteWrapper siteWrapper) {
		siteWrappers.add(siteWrapper);
		return this;
	}
	
	public BaseDataBuilder addAuthorities(RoleWrapper... roleWrappers) {
		BaseDataBuilder.roleWrappers.addAll(Arrays.asList(roleWrappers));
		return this;
	}
	
	public BaseDataBuilder addGroups(GroupWrapper... groupWrappers) {
		BaseDataBuilder.groupWrappers.addAll(Arrays.asList(groupWrappers));
		return this;
	}
	
	public static GroupWrapper group(String name, Object groupId) {
		for (GroupWrapper groupWrapper: groupWrappers) {
			Group group = groupWrapper.group;
			if (group.getGroupId().equals(groupId.toString()) &&
				group.getName().equals(name)) {
				
				return groupWrapper;
			}
		}
		
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
			Permission role = rw.role;
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
			buildGroups(null, groupWrapper, orders++);
		});
	}
	
	private void buildGroups(Group parent, GroupWrapper groupWrapper, int orders) {
		Group group = groupWrapper.group;
		group.setParent(parent);
		group.setOrders(orders);
		em.persist(group);
		
		childOrders = 0;
		groupWrapper.childGroupWrappers.forEach(childGroupWrapper -> {
			buildGroups(group, childGroupWrapper, childOrders++);
		});
	}
	
	private void buildRoles() {
		roleWrappers.forEach(roleWrapper -> {
			Permission role = roleWrapper.role;
			em.persist(role);
			
			roleWrapper.userGroupWrappers.forEach(userGroupWrapper -> {
				UserGroup userGroup = userGroupWrapper.userGroup;
//				userGroup.setAuthority(role);
				userGroup.setPermission(role);
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
				
				Orders orders = new Orders();
				menuGroupWrapper.menuWrappers.forEach(menuWrapper -> {
					buildMenu(menuGroup, menuWrapper, orders);
				});
			});
		});
	}
	
	private void buildMenu(MenuGroup menuGroup, MenuWrapper menuWrapper, Orders orders) {
		Menu menu = menuWrapper.menu;
		menu.setMenuGroup(menuGroup);
		menu.setOrders(orders.nextOrder());
		em.persist(menu);
		
		buildMenuAuthorities(menuWrapper);
		
		Orders childOrders = new Orders();
		menuWrapper.childMenuWrappers.forEach(childMenuWrapper -> {
			Menu childMenu = childMenuWrapper.menu;
			childMenu.setParent(menu);
			em.persist(childMenu);
			
			buildMenu(menuGroup, childMenuWrapper, childOrders);
//			buildMenuAuthorities(menuWrapper);
		});
	}
	
	private void buildMenuAuthorities(MenuWrapper menuWrapper) {
		Menu menu = menuWrapper.menu;
		
		//systemMaster 권한은 반드시 포함되게 처리한다.
		List<String> fixedGrantedAuthorities = new LinkedList<>();
		fixedGrantedAuthorities.add(DefinedUserAuthority.systemMaster.name());
		menuWrapper.grantedAuthorities.stream()
		.filter(authority -> {
			return authority.equals(DefinedUserAuthority.systemMaster.name()) ? false : true;
		})
		.forEach(authority -> {
			fixedGrantedAuthorities.add(authority);
		});
		
		
		fixedGrantedAuthorities.forEach(authority -> {
//			UserAuthority userAuthority = userAuthorityRepository.findByAuthority(authority);
			
			Permission userAuthority =
					em.createQuery("from UserAuthority ua where ua.authority = :authority", Permission.class)
						.setParameter("authority", authority).getSingleResult();
			if (userAuthority == null) {
				//정의 되지 않은 역할임
				throw new RuntimeException("정의 되지 않은 권한입니다. authority: " + authority);
			}
			
			PermissionMenu menuAuthority = new PermissionMenu();
			menuAuthority.setMenu(menu);
			menuAuthority.setPermission(userAuthority);
			em.persist(menuAuthority);
		});
	}
}


class Orders {
	int order;
	
	public int nextOrder() {
		return order++;
	}
}