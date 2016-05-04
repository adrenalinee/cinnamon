package org.cinnamon.apps.repository;

import org.cinnamon.apps.domain.UserApplication;
import org.cinnamon.apps.domain.UserApplication.UserApplicationId;
import org.cinnamon.core.domain.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 9. 30.
 * @author 신동성
 */
public interface UserApplicationRepository extends JpaRepository<UserApplication, UserApplicationId> {
	String deleteByUserBase(UserBase user);
}
