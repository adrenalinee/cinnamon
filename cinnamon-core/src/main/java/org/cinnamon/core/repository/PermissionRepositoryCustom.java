package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.vo.search.PermissionSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface PermissionRepositoryCustom {

	List<PermissionMenu> find(Long permissionId, Long menuGroupId);

	Page<Permission> search(PermissionSearch permissionSearch, Pageable pageable);

	List<PermissionMenu> getPermissionMenus(String authority, String uri);

	List<Permission> find(Long userGroupId, String uri);

}
