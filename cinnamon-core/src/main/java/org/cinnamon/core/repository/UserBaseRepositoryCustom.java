package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.vo.search.UserBaseSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author 신동성
 * @since 2016. 6. 7.
 */
public interface UserBaseRepositoryCustom<T extends UserBase> {
	
	Page<T> find(UserBaseSearch userBaseSearch, Pageable pageable);
}
