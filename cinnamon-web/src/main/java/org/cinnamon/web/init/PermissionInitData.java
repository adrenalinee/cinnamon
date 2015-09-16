package org.cinnamon.web.init;

import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Role;
import org.cinnamon.core.domain.RoleMenu;
import org.cinnamon.core.domain.Property;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.core.init.InitData;

/**
 * 
 * create date: 2015. 3. 27.
 * @author 동성
 *
 */
public class PermissionInitData implements InitData {
	
	EntityManager em;
	
	/**
	 * 
	 */
	public void save(EntityManager em) {
		this.em = em;
		
		master();
		user();
	}
	
	
	
	
	private void master() {
		Role permission = new Role();
		permission.setName("시스템 최고 운영 권한");
//		permission.setAuthority("ROLE_SYSTEM_MANAGER");
		
		permission.setAuthority(DefinedUserAuthority.systemMaster.name());
		em.persist(permission);
		
		UserGroup userGroup = new UserGroup();
		userGroup.setName("시스템 최고 운영자 그룹");
		userGroup.setRole(permission);
		em.persist(userGroup);
		
		//모든 메뉴를 추가
		List<Menu> menus = em.createQuery("from Menu", Menu.class).getResultList();
		menus.forEach(menu -> {
			RoleMenu permissionMenu = new RoleMenu();
			permissionMenu.setRole(permission);
			permissionMenu.setMenu(menu);
			permissionMenu.permitElse();
			
			em.persist(permissionMenu);
		});
		
		
		Property property = new Property();
//		property.setName(DefinedDBProperty.defaultSystemMasterGroup.name());
//		property.setValue(String.valueOf(userGroup.getUserGroupId()));
		
		em.persist(property);
	}
	
	
	private void user() {
		Role permission = new Role();
		permission.setName("기본 권한");
//		permission.setAuthority("ROLE_SYSTEM_MANAGER");
		
		permission.setAuthority(DefinedUserAuthority.normal.name());
		em.persist(permission);
		
		UserGroup userGroup = new UserGroup();
		userGroup.setName("일반 사용자 그룹");
		userGroup.setRole(permission);
		em.persist(userGroup);
		
		//메인 메뉴를 추가
//		List<Menu> menus = em.createQuery("select m from Menu m join m.menuGroup mg where mg.dimension = 'main'", Menu.class).getResultList();
//		menus.forEach(menu -> {
//			PermissionMenu permissionMenu = new PermissionMenu();
//			permissionMenu.setPermission(permission);
//			permissionMenu.setMenu(menu);
//			permissionMenu.allPermission();
//			
//			em.persist(permissionMenu);
//		});
		
		
		Property property = new Property();
//		property.setName(DefinedDBProperty.defaultUserGroup.name());
//		property.setValue(String.valueOf(userGroup.getUserGroupId()));
		
		em.persist(property);
	}
	
}
