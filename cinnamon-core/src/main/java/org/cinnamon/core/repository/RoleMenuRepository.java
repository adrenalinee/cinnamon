package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.UserAuthority;
import org.cinnamon.core.domain.MenuAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface RoleMenuRepository extends JpaRepository<MenuAuthority, Long> {
	
	MenuAuthority findByRoleAndMenu(UserAuthority role, Menu menu);
}
