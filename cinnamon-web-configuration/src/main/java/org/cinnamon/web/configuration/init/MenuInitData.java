package org.cinnamon.web.configuration.init;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.MenuType;
import org.cinnamon.core.init.InitData;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
public class MenuInitData implements InitData {
	
	EntityManager em;
	
	
	@Override
	public void save(EntityManager em) throws Exception {
		this.em = em;
		
		consoleSite();
	}
	
	
	private void consoleSite() {
		Site site = new Site();
		site.setSiteId("console");
		site.setName("콘솔");
		em.persist(site);
		
		
		MenuGroup configurationMenuGroup = getConfigurationMenuGroup(site);
		MenuGroup mainMenuGroup = getMainMenuGroup(site);
		
		
		Set<MenuGroup> consoleMenuGroups = new HashSet<MenuGroup>();
		consoleMenuGroups.add(configurationMenuGroup);
		consoleMenuGroups.add(mainMenuGroup);
		
		site.setMenuGroup(consoleMenuGroups);
	}
	
	
	private MenuGroup getMainMenuGroup(Site site) {
		MenuGroup menuGroup = new MenuGroup();
		menuGroup.setDimension("main");
		menuGroup.setName("메인 메뉴");
		
		menuGroup.setSite(site);
		em.persist(menuGroup);
		
		createMainSidebar(menuGroup);
		createMainHeaerRight(menuGroup);
		createMainHeaderLeft(menuGroup);
		
		return menuGroup;
	}
	
	
	private void createMainHeaderLeft(MenuGroup menuGroup) {
		Menu menu = new Menu();
		menu.setPosition(MenuPosition.headerLeft);
		menu.setName("메뉴");
		menu.setIconClass("fa fa-bars");
		menu.setOrders(0);
		menu.setMenuGroup(menuGroup);
		menu.setType(MenuType.component);
		em.persist(menu);
	}


	private void createMainHeaerRight(MenuGroup menuGroup) {
		Menu menu = new Menu();
		menu.setPosition(MenuPosition.headerRight);
		menu.setName("설정");
		menu.setIconClass("fa fa-gear");
		menu.setOrders(1);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		
		int order = 0;
		Menu childMenu = new Menu();
		childMenu.setName("기본 설정");
		childMenu.setUri("/configuration");
		childMenu.setIconClass("fa fa-cogs");
		childMenu.setOrders(order++);
		childMenu.setParent(menu);
		menu.setMenuGroup(menuGroup);
		em.persist(childMenu);
	}


	private MenuGroup getConfigurationMenuGroup(Site site) {
		MenuGroup menuGroup = new MenuGroup();
		menuGroup.setDimension("configuration");
		menuGroup.setName("기본설정 메뉴");
		menuGroup.setSite(site);
		
		em.persist(menuGroup);
		
		
		createConfigurationSidebar(menuGroup);
		createConfigHeaderRight(menuGroup);
		createConfigHeaderLeft(menuGroup);
		
		return menuGroup;
	}
	
	
	private void createConfigHeaderLeft(MenuGroup menuGroup) {
		Menu menu = new Menu();
		menu.setPosition(MenuPosition.headerLeft);
		menu.setName("메뉴");
		menu.setIconClass("fa fa-bars");
		menu.setOrders(0);
		menu.setMenuGroup(menuGroup);
		menu.setType(MenuType.component);
		em.persist(menu);
	}


	private void createConfigHeaderRight(MenuGroup menuGroup) {
		Menu menu = new Menu();
		menu.setPosition(MenuPosition.headerRight);
		menu.setName("설정");
		menu.setIconClass("fa fa-gear");
		menu.setOrders(0);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		
		int order = 0;
		Menu childMenu = new Menu();
		childMenu.setName("기본 설정");
		childMenu.setUri("/configuration");
		childMenu.setIconClass("fa fa-cogs");
		childMenu.setOrders(order++);
		childMenu.setParent(menu);
		menu.setMenuGroup(menuGroup);
		em.persist(childMenu);
	}


	private void createConfigurationSidebar(MenuGroup menuGroup) {
		int order = 0;
		
		Menu menu = new Menu();
//		configChildMenu.setMenuId("administratorMenu");
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("사용자");
		menu.setUri("/configuration/users");
		menu.setIconClass("glyphicon glyphicon-user");
		menu.setOrders(order++);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		menu = new Menu();
//		configChildMenu.setMenuId("administratorGroupMenu");
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("사용자 모임");
		menu.setUri("/configuration/userGroups");
		menu.setIconClass("fa fa-users");
		menu.setOrders(order++);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		menu = new Menu();
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("사이트");
		menu.setUri("/configuration/sites");
		menu.setIconClass("fa fa-sitemap");
		menu.setOrders(order++);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		menu = new Menu();
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("권한");
		menu.setUri("/configuration/permissions");
		menu.setIconClass("fa fa-shield");
		menu.setOrders(order++);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		menu = new Menu();
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("이메일");
		//menu.setUri("/configuration/emailServers");
		menu.setIconClass("fa fa-envelope");
		menu.setOrders(order++);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		Menu child = new Menu();
		child.setName("서버");
		child.setUri("/configuration/emailServers");
		child.setIconClass("fa fa-server");
		child.setOrders(0);
		child.setParent(menu);
		child.setMenuGroup(menuGroup);
		em.persist(child);
		
		child = new Menu();
		child.setName("템플릿");
		child.setUri("/configuration/emailTemplates");
		child.setIconClass("fa fa-envelope-o");
		child.setOrders(1);
		child.setParent(menu);
		child.setMenuGroup(menuGroup);
		em.persist(child);
		
		
		
		menu = new Menu();
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("코드");
		menu.setUri("/configuration/groups");
		menu.setIconClass("fa fa-folder");
		menu.setOrders(order++);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
		
		menu = new Menu();
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("첨부파일");
		menu.setUri("/configuration/files");
		menu.setIconClass("fa fa-upload");
		menu.setOrders(order++);
		menu.setMenuGroup(menuGroup);
		em.persist(menu);
	}
	
	
	
	
	private void createMainSidebar(MenuGroup menuGroup) {
		Menu menu = new Menu();
		menu.setPosition(MenuPosition.sidebar);
		menu.setName("현황판");
		menu.setUri("/dashboard");
		menu.setIconClass("fa fa-dashboard");
		menu.setOrders(0);
		em.persist(menu);
		menu.setMenuGroup(menuGroup);
	}
	
}
