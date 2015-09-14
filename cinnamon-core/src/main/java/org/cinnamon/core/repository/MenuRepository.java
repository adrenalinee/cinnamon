package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, QueryDslPredicateExecutor<Menu>, MenuRepositoryCustom {
	
	Menu findByUri(String uri);
	
	Page<Menu> findBy(@Param(value="position") MenuPosition position, Pageable pageable);
}
