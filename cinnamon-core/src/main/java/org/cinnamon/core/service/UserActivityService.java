package org.cinnamon.core.service;

import org.cinnamon.core.domain.UserActivity;
import org.cinnamon.core.domain.UserBase;

/**
 * 
 * create date: 2015. 5. 4.
 * @author 신동성
 *
 */
public interface UserActivityService<T extends UserBase> {

	void save(UserActivity userActivity);
	
	void addActivity(T user, Object activityType);

}
