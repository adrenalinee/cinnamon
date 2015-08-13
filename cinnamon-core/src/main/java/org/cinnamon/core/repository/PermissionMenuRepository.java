package org.cinnamon.core.repository;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.domain.QPermissionMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * create date: 2015. 6. 1.
 * @author 신동성
 *
 */
@Repository
public class PermissionMenuRepository {
	
	@Autowired
	EntityManager em;
	
	
	public PermissionMenu findByPermissionAndMenu(Permission permission, Menu menu) {
		QPermissionMenu permissionMenu = QPermissionMenu.permissionMenu;
		
		JPAQuery query = new JPAQuery(em);
		
		return query
				.from(permissionMenu)
				.where(permissionMenu.permission.eq(permission).and(permissionMenu.menu.eq(menu))).singleResult(permissionMenu);
	}
}
