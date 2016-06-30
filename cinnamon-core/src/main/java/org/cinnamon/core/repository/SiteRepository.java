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
	
//	/**
//	 * 기본 메뉴 그룹 삭제 부분 추가
//	 * @author 정명성
//	 * create date : 2016. 3. 17.
//	 * @param siteId
//	 * @param defaultMenuGroupId
//	 * @return
//	 */
//	Site findBySiteIdAndDefaultMenuGroupMenuGroupId(String siteId, Long defaultMenuGroupId);
	
	/**
	 * 메뉴 그룹 별 사이트 가져오기
	 * @author 정명성
	 * create date : 2016. 3. 21.
	 * @param menuGroupId
	 * @return
	 */
	Site findByMenuGroupMenuGroupId(Long menuGroupId);
}
