package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface MenuAuthorityRepository extends JpaRepository<PermissionMenu, Long> {
	
	PermissionMenu findByPermissionAndMenu(Permission permission, Menu menu);
}
