package org.cinnamon.core.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Role;
import org.cinnamon.core.domain.UserActivity;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.domain.UserPassword;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.domain.enumeration.UserActivityType;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.core.exception.BadRequestException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.RoleRepository;
import org.cinnamon.core.repository.PropertyRepository;
import org.cinnamon.core.repository.UserActivityRepository;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserGroupRepository;
import org.cinnamon.core.repository.UserPasswordRepository;
import org.cinnamon.core.repository.predicate.UserBasePredicate;
import org.cinnamon.core.service.listener.UserListener;
import org.cinnamon.core.vo.UserVo;
import org.cinnamon.core.vo.search.UserSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author 동성
 * @since 2015. 1. 28.
 */
@Service("configuration.userBaseService")
public class UserBaseService<T extends UserBase> {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserBaseRepository<T> userRepository;
	
	@Autowired
	UserActivityRepository userActivityRepository;
	
	@Autowired
	UserGroupRepository userGroupRepository;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	UserPasswordRepository userPasswordRepository;
	
//	@Autowired
//	EmailService emailService;
	
	@Autowired
	RoleRepository permissionRepository;
	
	List<UserListener> userListeners = new LinkedList<UserListener>();
	
	
	public void addListener(UserListener userListener) {
		logger.info("start");
		userListeners.add(userListener);
	}
	
	public void removeListener(UserListener userListener) {
		logger.info("start");
		userListeners.remove(userListener);
	}
	
	@Transactional(readOnly=true)
	public Page<T> search(UserSearch userSearch, Pageable pageable) {
		logger.info("start");
		return userRepository.findAll(UserBasePredicate.search(userSearch), pageable);
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public long getAllUserCount() {
		logger.info("start");
		
		return userRepository.count();
	}
	
	
	
	@Transactional(readOnly=true)
	public boolean exists(String userId) {
		return userRepository.exists(userId);
	}
	
	
	@Transactional
	public void save(T user) {
		logger.info("start");
		
		userRepository.save(user);
		
		//TODO 사용자 활동 로그
	}
	
	
	/**
	 * 비밀번호가 일치하는지 확인
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean isPasswordCorrect(String userId, String password) {
		logger.info("start");
		
		UserPassword userPassword = userPasswordRepository.findOne(userId);
		if (userPassword == null) {
			new RuntimeException("사용자 password가 생성되지 않았습니다. userId: " + userId);
		}
		
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		return e.matches(password, userPassword.getPassword());
	}
	
	
	/**
	 * 
	 * @param user
	 * @param password - 암호화 되지 않은 RAW 비밀번호
	 */
	@Transactional
	public void join(T user, String password) {
		logger.info("start");
		
		final String userId = user.getUserId();
		
		T existUser = userRepository.findOne(userId);
		if (existUser != null) {
			throw new BadRequestException("이미 사용중인 아이디 입니다. userId: " + userId);
		}
		
		
//		Property property = propertyRepository.findByName(DefinedDBProperty.defaultUserGroup);
//		if (property == null) {
//			new RuntimeException("defaultNormalGroup property 값이 없습니다.");
//		}
//		
//		Long defaultUserGroupId = property.getLongValue();
//		UserGroup userGroup = userGroupRepository.findById(defaultUserGroupId);
//		if (userGroup == null) {
//			new RuntimeException("defaultUserGroupId 이 존재하지 않습니다. defaultUserGroupId: " + defaultUserGroupId);
//		}
		
		
		Role permission = permissionRepository.findOne(DefinedUserAuthority.normal.name());
		UserGroup userGroup = permission.getDefaultUserGroup();
		if (userGroup == null) {
			new RuntimeException("systemMaster권한의 defaultUserGroup 이 존재하지 않습니다.");
		}
		
		
		
		//비번
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword(e.encode(password));
		userPassword.setUserId(userId);
//		userPasswordRepository.save(userPassword);
		
		
		user.setUserPassword(userPassword);
		user.getUserGroups().add(userGroup);
		
		userRepository.save(user);
		
		
		//사용자 활동 로그
		UserActivity userActivity = new UserActivity();
		userActivity.setUserId(user.getUserId());
		userActivity.setType(UserActivityType.join.name());
		
		userActivityRepository.persist(userActivity);
	}
	
	/**
	 * 가입
	 * 
	 * @param userVo
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public T join(UserVo userVo) {
		logger.info("start");
		
		final String userId = userVo.getUserId();
		T existUser = userRepository.findOne(userId);
		if (existUser != null) {
			throw new BadRequestException("이미 사용중인 아이디 입니다. userId: " + userVo.getUserId());
		}
		
		
//		Property property = propertyRepository.findByName(DefinedDBProperty.defaultUserGroup);
//		if (property == null) {
//			new RuntimeException("defaultNormalGroup property 값이 없습니다.");
//		}
//		
//		Long defaultUserGroupId = property.getLongValue();
//		UserGroup userGroup = userGroupRepository.findById(defaultUserGroupId);
//		if (userGroup == null) {
//			new RuntimeException("defaultUserGroupId 이 존재하지 않습니다. defaultUserGroupId: " + defaultUserGroupId);
//		}
		
		Role permission = permissionRepository.findOne(DefinedUserAuthority.normal.name());
		UserGroup userGroup = permission.getDefaultUserGroup();
		if (userGroup == null) {
			new RuntimeException("systemMaster권한의 defaultUserGroup 이 존재하지 않습니다.");
		}
		
		
		//비번
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword(e.encode(userVo.getPassword()));
		userPassword.setUserId(userId);
//		userPasswordRepository.save(userPassword);
		
		
		T user = (T) new UserBase();
		BeanUtils.copyProperties(userVo, user);
		user.setUserPassword(userPassword);
//		user.setUserGroup(userGroup);
		user.getUserGroups().add(userGroup);
		
		userRepository.save(user);
		
		
		UserActivity userActivity = new UserActivity();
		userActivity.setUserId(userId);
		userActivity.setType(UserActivityType.join.name());
		
		userActivityRepository.persist(userActivity);
		
		notifyAfterJoin(user);
		return user;
	}
	
	
	/**
	 * 
	 * @param userVo
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public T joinSystemMaster(UserVo userVo) {
		logger.info("start");
		
		final String userId = userVo.getUserId();
		T existUser = userRepository.findOne(userId);
		if (existUser != null) {
			throw new BadRequestException("이미 사용중인 사용자 아이디 입니다. userId: " + userVo.getUserId());
		}
		
		
		//그룹
//		Property property = propertyRepository.findByName(DefinedDBProperty.defaultSystemMasterGroup);
//		if (property == null) {
//			new RuntimeException("defaultSystemMasterGroup property 값이 없습니다.");
//		}
//		
//		System.out.println(ToStringBuilder.reflectionToString(property));
//		
//		Long defaultSystemMasterGroupId = (Long) property.getLongValue();
//		UserGroup userGroup = userGroupRepository.findById(defaultSystemMasterGroupId);
		
		Role permission = permissionRepository.findOne(DefinedUserAuthority.systemMaster.name());
		UserGroup userGroup = permission.getDefaultUserGroup();
		if (userGroup == null) {
			new RuntimeException("systemMaster권한의 defaultUserGroup 이 존재하지 않습니다.");
		}
		
		
		//비번
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword(e.encode(userVo.getPassword()));
		userPassword.setUserId(userId);
//		userPasswordRepository.save(userPassword);
		
		
		T user = (T) new UserBase();
		BeanUtils.copyProperties(userVo, user);
		user.setUserPassword(userPassword);
//		user.setAccepted(true);
//		user.setUserGroup(userGroup);
		user.getUserGroups().add(userGroup);
		userRepository.save(user);
		
		
		//사용자 활동 등록
		UserActivity userActivity = new UserActivity();
		userActivity.setUserId(userId);
		userActivity.setType(UserActivityType.join.name());
		
		userActivityRepository.persist(userActivity);
		
		
		notifyAfterJoin(user);
		
		return user;
	}
	
	
	/**
	 * 최초 사용자는 가입 승인이 되어 있다.
	 * 
	 * @param userVo
	 */
	@Transactional
	public void joinFirstSystemMaster(UserVo userVo) {
		logger.info("start");
		
		T user = joinSystemMaster(userVo);
		user.setAccepted(true);
		user.setAcceptedAt(new Date());
	}
	
	
//	/**
//	 * 
//	 * @param email
//	 * @throws MessagingException
//	 */
//	@Transactional
//	public void sendPasswordResetEmail(String email) throws MessagingException {
//		logger.info("start");
//		
//		T user = userRepository.findByEmail(email);
//		if (user == null) {
//			//TODO 없으면 그냥 무시할지 결정 필요.
//			throw new BadRequestException("해당 이메일을 사용하는 계정이 없습니다.");
//		}
//		
//		String subject = "비밀번호를 변경 할 수 있습니다.";
//		String message = "";
//		
//		emailService.send(email, subject, message);
//	}
	
	
	/**
	 * 
	 * @param userId
	 */
	@Transactional
	public void sendValidationEmail(String userId) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
		if (user == null) {
			throw new NotFoundException("등록되지 않은 사용자 입니다. userId: " + userId);
		}
		
//		String email = user.getEmail();
		
		
		//TODO 이메일 발송..
	}
	
	
	
	@Transactional
	public void changeUseStatus(String userId, UseStatus useStatus) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
		if (user == null) {
			throw new NotFoundException("등록되지 않은 사용자 입니다. userId: " + userId);
		}
		
		user.setUseStatus(useStatus);
		
	}
	
	
	/**
	 * 사용자 서비스 실행에 대해서 리스너에게 노티를 준다.
	 * @param user
	 */
	private void notifyAfterJoin(T user) {
		for (UserListener listener: userListeners) {
			try {
				listener.afterJoin(user);
			} catch (Exception ex) {
				throw new RuntimeException("afterJoin 실행중에 오류 발생");
			}
		}
	}
	
	
//	public static void main(String[] args) {
//		
//		
//		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
//		for (int i = 0; i < 10; i++) {
//			System.out.println(e.encode("qwerty"));
//		}
//	}
}
