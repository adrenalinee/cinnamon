package org.cinnamon.core.service;

import org.cinnamon.core.domain.UserActivity;
import org.cinnamon.core.repository.UserActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RDB에 저장하는 버전
 * 
 * create date: 2015. 5. 4.
 * @author 신동성
 *
 */
@Service
public class UserActivityServiceImpl implements UserActivityService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserActivityRepository userActivityRepository;
	
	
	@Override
	public void save(UserActivity userActivity) {
		logger.info("start");
		
		userActivityRepository.persist(userActivity);
	}
}
