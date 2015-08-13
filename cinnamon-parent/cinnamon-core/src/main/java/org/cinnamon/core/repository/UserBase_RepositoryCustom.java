package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.vo.search.UserSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author 신동성
 *
 */
public interface UserBase_RepositoryCustom<T extends UserBase> {

//	public abstract User findByEmail(String email);

	Page<T> search1(UserSearch userSearch, Pageable pageable);

//	long allCount();
	
	
}
