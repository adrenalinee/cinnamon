package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * create date: 2015. 6. 17.
 * @author 신동성
 *
 */
public interface UserPasswordRepository<T extends UserBase> extends JpaRepository<UserPassword, String> {
	
}
