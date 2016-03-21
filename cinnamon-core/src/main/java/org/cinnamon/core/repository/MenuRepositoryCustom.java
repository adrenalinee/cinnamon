package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.vo.search.MenuSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface MenuRepositoryCustom {
	
	Page<Menu> find(MenuSearch menuSearch, Pageable pageable);
	
	List<Menu> find(String dimension, MenuPosition position, List<String> grantedAuthorities);
	
	List<Menu> findByAuthority(String authority);
//
//	List<Menu> getSitePermisionMenus(String site, String dimension, MenuPosition position, List<String> authorities);
//
//	List<Menu> getSitePermisionMenus(String site, String dimension, MenuPosition position, String authority);
//
//	Page<Menu> search(MenuSearch menuSearch, Pageable pageable);

}
