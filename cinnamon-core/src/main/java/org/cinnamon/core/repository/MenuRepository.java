package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, QueryDslPredicateExecutor<Menu>, MenuRepositoryCustom {
	
	Menu findByUri(String uri);
	
//	Page<Menu> findAll(MenuSearch menuSearch, Pageable pageable);
}
