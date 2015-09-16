package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Role;
import org.cinnamon.core.domain.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {
	
	RoleMenu findByRoleAndMenu(Role role, Menu menu);
}
