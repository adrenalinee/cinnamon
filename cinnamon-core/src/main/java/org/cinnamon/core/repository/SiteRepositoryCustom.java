package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.Site;
import org.cinnamon.core.vo.search.SiteSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface SiteRepositoryCustom {

	Page<Site> search(SiteSearch siteSearch, Pageable pageable);

	List<Site> findAll();

}
