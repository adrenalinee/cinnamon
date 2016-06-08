package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserBase;
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
public interface UserGroupRepositoryCustom<T extends UserBase> {

	Page<UserGroup> search(UserGroupSearch userGroupSearch, Pageable pageable);

}
