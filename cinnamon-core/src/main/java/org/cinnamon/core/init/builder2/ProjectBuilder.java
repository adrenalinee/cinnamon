package org.cinnamon.core.init.builder2;

import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.domain.enumeration.MenuPosition;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class ProjectBuilder {
	
	List<SiteWrapper> siteWrappers = new LinkedList<>();
	
	List<RoleWrapper> roleWrappers = new LinkedList<>();
	
	
	
	public ProjectBuilder addSite(SiteWrapper siteWrapper) {
		siteWrappers.add(siteWrapper);
		return this;
	}
	
	public ProjectBuilder addRole(RoleWrapper roleWrapper) {
		roleWrappers.add(roleWrapper);
		return this;
	}
	
	public static RoleWrapper role(String name, String authority) {
		return new RoleWrapper(name, authority);
	}
	
	public static SiteWrapper site(String name, String siteId) {
		return new SiteWrapper(name, siteId);
	}
	
	public static MenuGroupWrapper menuGroup(String name, String dimension) {
		return new MenuGroupWrapper(name, dimension);
	}
	
	public static MenuWrapper menu(String name, MenuPosition position) {
		return new MenuWrapper(name, position);
	}
	
	
	
	
	public void build() {
		
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
				});
			});
		});
	}
}
