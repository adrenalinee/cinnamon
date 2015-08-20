package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface SiteRepository extends JpaRepository<Site, String>, SiteRepositoryCustom {

}
