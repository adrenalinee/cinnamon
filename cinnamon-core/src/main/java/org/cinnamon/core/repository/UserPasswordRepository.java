package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * create date: 2015. 6. 17.
 * @author 신동성
 *
 */
public interface UserPasswordRepository extends JpaRepository<UserPassword, String> {

}
