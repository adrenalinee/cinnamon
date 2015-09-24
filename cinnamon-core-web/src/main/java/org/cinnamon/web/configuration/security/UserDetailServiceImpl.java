package org.cinnamon.web.configuration.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.cinnamon.core.domain.Role;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserPasswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
	UserPasswordRepository userPasswordRepository;
	
	@Autowired
	EntityManager em;
	
	
	@Override
//	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("start");
		
		UserBase user = userRepository.findOne(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("사용자가  존재 하지 않습니다. username = " + username);
		}
		
//		UserPassword userPassword = userPasswordRepository.getNowPassword(user.getUserId());
//		if (userPassword == null) {
//			throw new RuntimeException("비밀번호가 설정되지 않았습니다! userId: " + username);
//		}
//		String password = userPassword.getPassword();
		
		Set<RoleGrantedAuthority> permissionGrantedAuthorities = new HashSet<RoleGrantedAuthority>();
		for (UserGroup userGroup: user.getUserGroups()) {
			Role permission = userGroup.getRole();
			
			RoleGrantedAuthority permissionGrantedAuthority = new RoleGrantedAuthority(permission);
			permissionGrantedAuthorities.add(permissionGrantedAuthority);
		}
		
//		Permission permission = user.getUserGroup().getPermission();
//		PermissionGrantedAuthority permissionGrantedAuthority = new PermissionGrantedAuthority(permission);
//		permissionGrantedAuthorities.add(permissionGrantedAuthority);
		
		return new UserDetailImpl(user, permissionGrantedAuthorities);
	}

}
