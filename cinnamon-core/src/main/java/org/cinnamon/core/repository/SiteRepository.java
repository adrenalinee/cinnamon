package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface SiteRepository extends JpaRepository<Site, String>, QueryDslPredicateExecutor<Site>, SiteRepositoryCustom {

}
