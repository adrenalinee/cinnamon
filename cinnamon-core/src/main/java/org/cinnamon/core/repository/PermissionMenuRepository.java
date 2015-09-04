package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
@RepositoryRestResource(exported=false)
public interface PermissionMenuRepository extends JpaRepository<PermissionMenu, Long> {
	
	PermissionMenu findByPermissionAndMenu(Permission permission, Menu menu);
}
