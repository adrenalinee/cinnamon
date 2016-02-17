package org.cinnamon.core.service;

import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.repository.UserAuthorityRepository;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserGroupRepository;
import org.cinnamon.core.vo.search.UserGroupSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	UserAuthorityRepository userAuthorityRepository;
	
	@Autowired
	UserBaseRepository<T> userRepository;
	
	@Transactional
	public UserGroup save(UserGroup userGroup) {
		logger.info("start");
		
		return userGroupRepository.save(userGroup);
	}
	
	
	@Transactional(readOnly=true)
	public UserGroup get(Long userGroupId) {
		logger.info("start");
		
		return userGroupRepository.findOne(userGroupId);
	}
	
	
	@Transactional(readOnly=true)
	public Page<UserGroup> getList(UserGroupSearch userGroupSearch, Pageable pageable) {
		logger.info("start");
		
		return userGroupRepository.find(userGroupSearch, pageable);
	}
	
	
	@Transactional
	public void addMember(Long userGroupId, String userId) {
		logger.info("start");
		
		UserBase user = userRepository.findOne(userId);
		if (user == null) {
			throw new RuntimeException("등록되지 않은 사용자 입니다. userId: " + userId);
		}
		
		UserGroup userGroup = userGroupRepository.findOne(userGroupId);
		if (userGroup == null) {
			throw new RuntimeException("등록되지 않은 사용자 그룹입니다. userGroupId: " + userGroupId);
		}
		
		user.getUserGroups().add(userGroup);
	}
	
	
	@Transactional
	public void addMember(Object authority, String userId) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
		if (user == null) {
			throw new RuntimeException("등록되지 않은 사용자 입니다. userId: " + userId);
		}
		
		Permission userAuthority = userAuthorityRepository.findByAuthority(authority.toString());
		if (userAuthority == null) {
			throw new RuntimeException("등록되지 않은 authority 입니다. authority: " + authority);
		}
		
		UserGroup userGroup = userAuthority.getDefaultUserGroup();
		if (userGroup == null) {
			throw new RuntimeException("기본 사용자 그룹이 등록되지 않았습니다. authority: " + authority);
		}
		
		user.getUserGroups().add(userGroup);
	}
	
	
	@Transactional
	public void removeMember(Object authority, String userId) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
		if (user == null) {
			throw new RuntimeException("등록되지 않은 사용자 입니다. userId: " + userId);
		}
		
		Permission userAuthority = userAuthorityRepository.findByAuthority(authority.toString());
		if (userAuthority == null) {
			throw new RuntimeException("등록되지 않은 authority 입니다. authority: " + authority);
		}
		
		UserGroup userGroup = userAuthority.getDefaultUserGroup();
		if (userGroup == null) {
			throw new RuntimeException("기본 사용자 그룹이 등록되지 않았습니다. authority: " + authority);
		}
		
		user.getUserGroups().remove(userGroup);
	}
}
