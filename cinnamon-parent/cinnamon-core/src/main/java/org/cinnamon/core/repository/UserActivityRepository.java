package org.cinnamon.core.repository;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author 동성
 * @since 2015. 1. 28.
 */
@Repository("configuration.userActivityRepository")
public class UserActivityRepository {
	
	@Autowired
	EntityManager em;

	public void persist(UserActivity userActivity) {
		em.persist(userActivity);
	}
	
	
}
