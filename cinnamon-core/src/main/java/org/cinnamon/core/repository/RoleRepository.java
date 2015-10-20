package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface RoleRepository extends JpaRepository<UserAuthority, String>, RoleRepositoryCustom {
	
//	Role findByAuthority(@Param("authority") String authority);
}
