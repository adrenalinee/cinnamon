package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.vo.search.MenuGroupSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface MenuGroupRepositoryCustom {

	Page<MenuGroup> find(MenuGroupSearch menuGroupSearch, Pageable pageable);

	List<MenuGroup> find(String siteId);

	List<MenuGroup> getSiteScene(String siteId);

	MenuGroup getSiteMenuPosition(String siteId, String dimension);

}
