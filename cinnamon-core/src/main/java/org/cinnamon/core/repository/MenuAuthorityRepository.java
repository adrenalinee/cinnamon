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
public interface MenuAuthorityRepository extends JpaRepository<MenuAuthority, Long> {
	
	MenuAuthority findByAuthorityAndMenu(UserAuthority authority, Menu menu);
}
