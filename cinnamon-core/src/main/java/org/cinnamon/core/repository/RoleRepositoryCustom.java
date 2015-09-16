package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.Role;
import org.cinnamon.core.domain.RoleMenu;
import org.cinnamon.core.vo.search.RoleSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface RoleRepositoryCustom {

	List<RoleMenu> find(String authority, Long menuGroupId);

	Page<Role> search(RoleSearch roleSearch, Pageable pageable);

	List<RoleMenu> getRoleMenus(String authority, String uri);

	List<Role> find(Long userGroupId, String uri);

}
