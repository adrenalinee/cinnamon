package org.cinnamon.core.service;

import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.exception.BadRequestException;
import org.cinnamon.core.repository.UserAuthorityRepository;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserGroupRepository;
import org.cinnamon.core.vo.UserGroupVo;
import org.cinnamon.core.vo.search.UserGroupSearch;
import org.dozer.Mapper;
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
	
	@Autowired
	Mapper beanMapper;
	
	@Autowired
	UserAuthorityRepository permissionRepository;
	
	@Transactional
	public UserGroup save(UserGroupVo userGroupVo) {
		logger.info("start");
		
		UserGroup userGroup = beanMapper.map(userGroupVo, UserGroup.class);
		return userGroupRepository.save(userGroup);
	}
	
	
	/**
	 * 정보 수정
	 * 
	 * @param userGroupId
	 * @param userGroupVo
	 * @return
	 */
	@Transactional
	public UserGroup modify(Long userGroupId, UserGroupVo userGroupVo) {
		logger.info("start");
		
		UserGroup userGroup = userGroupRepository.findOne(userGroupId);
		if (userGroup == null) {
			throw new RuntimeException("등록되지 않은 사용자 그룹입니다. userGroupId: " + userGroupId);
		}
		
		beanMapper.map(userGroupVo, userGroup);
		
		return userGroup;
	}
	
	
	
	@Transactional(readOnly=true)
	public UserGroup get(Long userGroupId) {
		logger.info("start");
		
		return userGroupRepository.findOne(userGroupId);
	}
	
	
	@Transactional(readOnly=true)
	public Page<UserGroup> getList(UserGroupSearch userGroupSearch, Pageable pageable) {
		logger.info("start");
		
		return userGroupRepository.search(userGroupSearch, pageable);
	}
	
	
	@Transactional
	public void addMember(Long userGroupId, String userId) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
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
	public void addMemberByAuthority(Object authority, String userId) {
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
	
	/**
	 * 사용자 그룹 권한 등록
	 * @author 정명성
	 * @create date : 2016. 4. 29.
	 * @param userGroupId
	 * @param permissionId
	 */
	@Transactional
	public void submitUserGroupToPermission(Long userGroupId, Long permissionId) {
		logger.info("start");
		UserGroup userGroup = userGroupRepository.findOne(userGroupId);
		if (userGroup == null) {
			throw new BadRequestException("userGroup이 존재하지 않습니다. userGroupId:" + userGroupId);
		}
		Permission permission = permissionRepository.findOne(permissionId);
		if (permission == null) {
			throw new BadRequestException("permission이 존재하지 않습니다. permissionId:" + permissionId);
		}
		
		userGroup.setPermission(permission);
	}
}
