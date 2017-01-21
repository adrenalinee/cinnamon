package org.cinnamon.core.security;

import javax.annotation.PostConstruct;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserPasswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author 동성
 * @since 2014. 12. 26.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserBaseRepository<UserBase> userRepository;
	
	@Autowired
	UserPasswordRepository<UserBase> userPasswordRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private TransactionTemplate transactionTemplate;
	
	
	@PostConstruct
	public void postConstruct() {
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	
	@Cacheable(cacheNames="UserDetails", key="#username")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("start");
		
		return transactionTemplate.execute(status -> {
			UserBase user = userRepository.findOne(username);
//			System.out.println(ToStringBuilder.reflectionToString(user));
			
			if (user == null) {
				throw new UsernameNotFoundException("사용자가  존재 하지 않습니다. username = " + username);
			}
			
			return new UserDetailImpl(user/*, permissionGrantedAuthorities*/);
		});
	}

}
