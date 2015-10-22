package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.UserAuthority;
import org.cinnamon.core.domain.MenuAuthority;
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

	List<MenuAuthority> find(String authority, Long menuGroupId);

	Page<UserAuthority> search(AuthoritySearch roleSearch, Pageable pageable);

	List<MenuAuthority> getMenuAuthoritues(String authority, String uri);

	List<UserAuthority> find(Long userGroupId, String uri);

}
