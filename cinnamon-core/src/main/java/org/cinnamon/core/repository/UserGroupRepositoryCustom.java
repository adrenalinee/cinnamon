package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.vo.search.UserGroupSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface UserGroupRepositoryCustom {

	Page<UserGroup> search(UserGroupSearch userGroupSearch, Pageable pageable);

}
