package org.cinnamon.core.service;

import org.cinnamon.core.domain.Role;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.repository.RoleRepository;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * create date: 2015. 3. 30.
 * @author 동성
 *
 */
@Service
public class UserGroupService<T extends UserBase> {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserGroupRepository userGroupRepository;
	
	@Autowired
	RoleRepository permissionRepository;
	
	@Autowired
	UserBaseRepository<T> userRepository;
	
	
	@Transactional
	public void addMember(String authority, String userId) {
		logger.info("start");
		
		UserBase user = userRepository.findOne(userId);
		if (user == null) {
			throw new RuntimeException("등록되지 않은 사용자 입니다. userId: " + userId);
		}
		
		Role permission = permissionRepository.findOne(authority);
		if (permission == null) {
			throw new RuntimeException("등록되지 않은 authority 입니다. authority: " + authority);
		}
		
		UserGroup userGroup = permission.getDefaultUserGroup();
		if (userGroup == null) {
			throw new RuntimeException("기본 사용자 그룹이 등록되지 않았습니다. authority: " + authority);
		}
		
		user.getUserGroups().add(userGroup);
	}
}
