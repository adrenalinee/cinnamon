package org.cinnamon.core.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.domain.UserPassword;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.enumeration.DefinedUserActivity;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.core.exception.BadRequestException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.GroupRepository;
import org.cinnamon.core.repository.PropertyRepository;
import org.cinnamon.core.repository.UserAuthorityRepository;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserGroupRepository;
import org.cinnamon.core.repository.UserPasswordRepository;
import org.cinnamon.core.repository.predicate.UserBasePredicate;
import org.cinnamon.core.service.userListener.AfterUserJoinListener;
import org.cinnamon.core.vo.UserBaseVo;
import org.cinnamon.core.vo.UserJoinVo;
import org.cinnamon.core.vo.search.UserBaseSearch;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


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
	
//	@Autowired
//	UserActivityRepository userActivityRepository;
	
	@Autowired
	UserGroupRepository userGroupRepository;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	UserPasswordRepository<T> userPasswordRepository;
	
//	@Autowired
//	EmailService emailService;
	
	@Autowired
	UserAuthorityRepository permissionRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	UserActivityService<T> userActivityService;
	
	@Autowired
	Mapper beanMapper;
	
	List<AfterUserJoinListener<T>> afterUserJoinListeners = new LinkedList<AfterUserJoinListener<T>>();
	
	
	public void addAfterUserJoinListener(AfterUserJoinListener<T> afterUserJoinListener) {
		afterUserJoinListeners.add(afterUserJoinListener);
	}
	
	public void removeAfterUserJoinListener(AfterUserJoinListener<T> afterUserJoinListener) {
		afterUserJoinListeners.remove(afterUserJoinListener);
	}
	
//	@Transactional(readOnly=true)
//	public Page<T> search(UserBaseSearch userSearch, Pageable pageable) {
//		logger.info("start");
//		return userRepository.findAll(UserBasePredicate.search(userSearch), pageable);
//	}
	
	
	
	@Transactional(readOnly=true)
	public Page<T> getList(UserBaseSearch userSearch, Pageable pageable) {
		logger.info("start");
		
		return userRepository.findAll(UserBasePredicate.search(userSearch), pageable);
	}
	
	
	@Transactional(readOnly=true)
	public UserBase get(String userId) {
		logger.info("start");
		return userRepository.findOne(userId);
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
	
//	/**
//	 * 
//	 * @param userId
//	 * @param userVo - 갱신할 값(null이 아닌 값만 갱신된다)
//	 * @throws Exception
//	 */
//	@Transactional
//	public void modify(String userId, Map<String, ?> source) throws Exception {
//		logger.info("start");
//		
//		T existUser = userRepository.findOne(userId);
//		if (existUser == null) {
//			throw new NotFoundException("존재하지 않는 회원입니다. userId: " + userId);
//		}
//		
//		MapObjectMerger.copy(source, existUser);
//		
////		ObjectMerger.merge(user, existUser);
//	}
	
	
	/**
	 * 
	 * @param userId
	 * @param userVo - 갱신할 값(null이 아닌 값만 갱신된다)
	 * @throws Exception
	 */
	@Transactional
	public void merge(T user) {
		logger.info("start");
		
		String userId = user.getUserId();
		T existUser = userRepository.findOne(userId);
		if (existUser == null) {
			throw new NotFoundException("존재하지 않는 회원입니다. userId: " + userId);
		}
		
		beanMapper.map(user, existUser);
	}
	
	
	@Transactional
	public void save(String userId, UserBaseVo userBaseVo) {
		logger.info("start");
		
		T existUser = userRepository.findOne(userId);
		if (existUser == null) {
			throw new NotFoundException("존재하지 않는 회원입니다. userId: " + userId);
		}
		
		beanMapper.map(userBaseVo, existUser);
	}
	
	
	
//	/**
//	 * 비밀번호가 일치하는지 확인
//	 * 
//	 * @param userId
//	 * @param password
//	 * @return
//	 */
//	@Transactional(readOnly=true)
//	public boolean isPasswordCorrect(String userId, String password) {
//		logger.info("start");
//		
//		UserPassword userPassword = userPasswordRepository.findOne(userId);
//		if (userPassword == null) {
//			new RuntimeException("사용자 password가 생성되지 않았습니다. userId: " + userId);
//		}
//		
//		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
//		return e.matches(password, userPassword.getPassword());
//	}
	
	
	/**
	 * 
	 * @param user
	 * @param password - 암호화 되지 않은 RAW 비밀번호
	 */
	@Transactional
	public void join(T user, String password) {
		logger.info("start");
		
		final String userId = user.getUserId();
		
		//국가 코드가 정상인지 확인!!
		String nation = user.getNation();
		if (!StringUtils.isEmpty(nation)) {
			if (!groupRepository.exists(nation)) {
				throw new BadRequestException("지정되지 않은 국가 코드 입니다. nation: " + nation);
			}
		}
		
		//TODO 사용불가한 아이디 인지 확인
		
		
		T existUser = userRepository.findOne(userId);
		if (existUser != null) {
			throw new BadRequestException("이미 사용중인 아이디 입니다. userId: " + userId);
		}
		
		
		Permission permission = permissionRepository.findByAuthority(DefinedUserAuthority.user.name());
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
		
		
		//사용자 활동기록 등록
		userActivityService.addActivity(user, DefinedUserActivity.signup);
		notifyAfterJoin(user);
	}
	
	/**
	 * 가입
	 * 
	 * @param userVo
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public T join(String creator, UserJoinVo userVo) {
		logger.info("start");
		
		final String userId = userVo.getUserId();
		T existUser = userRepository.findOne(userId);
		if (existUser != null) {
			throw new BadRequestException("이미 사용중인 아이디 입니다. userId: " + userVo.getUserId());
		}
		
		
		Permission permission = permissionRepository.findByAuthority(DefinedUserAuthority.user.name());
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
		user.getUserGroups().add(userGroup);
		user.setCreator(creator);
		
		userRepository.save(user);
		
		//사용자 활동기록 등록
		userActivityService.addActivity(user, DefinedUserActivity.signup);
		
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
	public T joinSystemMaster(UserJoinVo userVo) {
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
		
		Permission permission = permissionRepository.findByAuthority(DefinedUserAuthority.systemMaster.name());
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
		
		
		//사용자 활동기록 등록
		userActivityService.addActivity(user, DefinedUserActivity.signup);
		
		notifyAfterJoin(user);
		
		return user;
	}
	
	
	/**
	 * 최초 사용자는 가입 승인이 되어 있다.
	 * 
	 * @param userVo
	 */
	@Transactional
	public void joinFirstSystemMaster(UserJoinVo userVo) {
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
	 * 삭제
	 * 
	 * 실제 지우지 않고 상태(useStatus)만 삭제상태로 바꾼다.
	 * 
	 * 
	 * 
	 * @param userId
	 */
	@Transactional
	public void leave(String userId) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
		if (user == null) {
			throw new NotFoundException("userExtension이 생성되지 않았습니다. userId: " + userId);
		}
		
		try {
			//TODO 활동 기록이 있으면 조인된 테이블과 외래키로 역여 있기때문에 삭제 되지 않는 점을 이용해서 일단 지워보고 안지워 지면 상태만 바꾸고 향후 관련 모든 기록을 지워야 함
			// 이렇게 처리하는것이 맞을지 무조건 상태만 변경하는 형태로 가야할지 검토 및 결정 필요
			userRepository.delete(user);
		} catch (DataAccessException e) {
			logger.warn("할동 기록이 있어 해당 사용자정보를 삭제 할 수 없음. 사용상태만 deleted로 변경함. userId: " + userId, e);
			
			user.setUseStatus(UseStatus.deleted);
		}
		
		userActivityService.addActivity(user, DefinedUserActivity.deleteAccount);
	}
	
	
	/**
	 * 사용자 서비스 실행에 대해서 리스너에게 노티를 준다.
	 * @param user
	 */
	private void notifyAfterJoin(T user) {
		for (AfterUserJoinListener listener: afterUserJoinListeners) {
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
