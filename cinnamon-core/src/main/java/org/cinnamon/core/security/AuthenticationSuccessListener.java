package org.cinnamon.core.security;

import org.cinnamon.core.service.UserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 로그인 성공후 호출됨.
 * 사용자 로그인 기록을 저장함.
 * 
 * create date: 2015. 5. 4.
 * @author 신동성
 *
 */
//@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserActivityService userActivityService;
	
	
	@Transactional
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		logger.info("start");
		
		
		Authentication authentication = event.getAuthentication();
		System.out.println(authentication);
		if (authentication != null) {
			System.out.println(authentication.getName());
		}
		
		
//		Authentication authentication = event.getAuthentication();
//		String userId = authentication.getName();
//		System.out.println("userId: " + userId);
//		
//		UserActivity userActivity = new UserActivity();
//		userActivity.setUserId(userId);
//		userActivity.setType(UserActivityType.login.name());
//		
//		userActivityService.save(userActivity);
	}

}
