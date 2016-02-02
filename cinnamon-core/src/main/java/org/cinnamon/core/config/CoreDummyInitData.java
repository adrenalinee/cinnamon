package org.cinnamon.core.config;

import java.util.Random;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserPassword;
import org.cinnamon.core.domain.enumeration.EntityType;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.util.RandomUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 신동성
 * @since 2016. 2. 2.
 */
@Component
public class CoreDummyInitData implements InitData {
	
	@Autowired
	UserBaseRepository<UserBase> userRepository;
	
	
	@Override
	public void save(EntityManager em) throws Exception {
		for (int i = 0; i < 111; i++) {
			String newUserId = null;
			for (int c = 0; c < 10; c++) {
				newUserId = RandomUtil.getRandom(10);
				if (userRepository.findOne(newUserId) == null) {
					break;
				}
			}
			if (newUserId == null) {
				continue;
			}
			
			
			
			UserBase user = new UserBase();
			user.setName("더미" + newUserId);
			user.setUserId(newUserId);
			user.setEmail("dummy" + i + "@company.com");
			user.setNation("KR");
			user.setEntityType(EntityType.dummy);
			
			
			int age = new Random().nextInt(40) + 19;
			DateTime birthday = DateTime.now().minusYears(age);
			int month = new Random().nextInt(12);
			birthday.minusMonths(month);
			int day = new Random().nextInt(30);
			birthday.minusDays(day);
			
			
			
			userRepository.save(user);
			
			
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encryptedPassword = passwordEncoder.encode("1234");
			UserPassword userPassword = new UserPassword();
			userPassword.setPassword(encryptedPassword);
		}
	}
	
	@Override
	public int order() {
		return 0;
	}

}
