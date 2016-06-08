package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author 신동성
 * 
 * @param <T> UserBase를 확장한 객체
 */
public interface UserBaseRepository<T extends UserBase>
	extends JpaRepository<T, String>, UserBaseRepositoryCustom<T> {
	
	T findFirst1ByEmail(String email);
	
	T findByUserIdAndEmailAndUseStatus(String userId, String email, UseStatus useStatus);
}
