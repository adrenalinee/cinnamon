package org.cinnamon.core.repository;

import org.cinnamon.core.domain.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long>, MenuGroupRepositoryCustom {
	
	MenuGroup findByDimension(String dimension);
	
//	/**
//	 * 메뉴 그룹 조회
//	 * @author 정명성
//	 * create date : 2016. 3. 3.
//	 * @param menuGroupId
//	 * @return
//	 */
//	MenuGroup findByMenuGroupId(Long menuGroupId);
	
	
	MenuGroup findByMenuGroupIdAndSiteSiteId(Long menuGroupId, String siteId);
	
	long countBySiteSiteId(String siteId);
}
