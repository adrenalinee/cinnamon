package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.vo.search.AuthoritySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface UserAuthorityRepositoryCustom {

	List<PermissionMenu> find(String authority, Long menuGroupId);

	Page<Permission> search(AuthoritySearch roleSearch, Pageable pageable);

	List<PermissionMenu> getMenuAuthoritues(String authority, String uri);

	List<Permission> find(Long userGroupId, String uri);

}
