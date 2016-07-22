package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface UserAuthorityRepository extends JpaRepository<Permission, Long>, UserAuthorityRepositoryCustom {
	
	Permission findByAuthority(String authority);
	
	//Permission findFirst1ByAuthorityIn(List<String> authorities);
}
