package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * 
 * @author 신동성
 * 
 * @param <T> UserBase를 확장한 객체
 */
public interface UserBaseRepository<T extends UserBase> extends JpaRepository<T, String>, QueryDslPredicateExecutor<T> /*, UserBaseRepositoryCustom*/ {
	
	
//	@Query("select u from #{#entityName} as u where u.email = ?1")
	T findByEmail(String email);
	
}