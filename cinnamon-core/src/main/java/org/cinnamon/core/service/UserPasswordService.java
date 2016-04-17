package org.cinnamon.core.service;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserPassword;
import org.cinnamon.core.exception.BadRequestException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.UserBaseRepository;
import org.cinnamon.core.repository.UserPasswordRepository;
import org.cinnamon.core.vo.UserPasswordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author shindongseong
 * @since 2016. 4. 13.
 */
@Service
public class UserPasswordService<T extends UserBase> {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserBaseRepository<T> userRepository;
	
	@Autowired
	UserPasswordRepository<T> userPasswordRepository;
	
	
	/**
	 * 비밀번호 초기화
	 * changeKey가 일치하면 무조건 비밀번호 변경
	 * 
	 * @param userId
	 * @param newPassword
	 * @param changeKey
	 */
	@Transactional
	public void change(String userId, String newPassword, String changeKey) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
		if (user == null) {
			throw new NotFoundException("존재하지 않는 회원입니다. userId: " + userId);
		}
		
		UserPassword userPassword = user.getUserPassword();
		
		if (!changeKey.equals(userPassword.getChangeKey())) {
			throw new BadRequestException("changeKey가 일치하지 않습니다. userId: " + userId);
		}
		
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		userPassword.setPassword(e.encode(newPassword));
	}
	
	
	/**
	 * 비밀번호 변경
	 * 현재 비밀번호를 알고 있으면 새로운 비밀번호로 변경
	 * 
	 * @param userId
	 * @param userPasswordVo
	 */
	@Transactional
	public void change(String userId, UserPasswordVo userPasswordVo) {
		logger.info("start");
		
		T user = userRepository.findOne(userId);
		if (user == null) {
			throw new NotFoundException("존재하지 않는 회원입니다. userId: " + userId);
		}
		
		UserPassword userPassword = user.getUserPassword();
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		if (!e.matches(userPasswordVo.getCurrentPassword(), userPassword.getPassword())) {
			throw new BadRequestException("현재 비밀번호가 잘못되었습니다. userId: " + userId);
		}
		
		if (!userPasswordVo.getNewPassword().equals(userPasswordVo.getNewPasswordConfirm())) {
			throw new BadRequestException("새로운 비밀번호와 확인용 비밀번호가 일치하지 않습니다. userId: " + userId);
		}
		
		userPassword.setChangeKey(null);
		userPassword.setPassword(e.encode(userPasswordVo.getNewPassword()));
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
}
