package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, PermissionRepositoryCustom {
	
	Permission findByAuthority(@Param("authority") String authority);
}
