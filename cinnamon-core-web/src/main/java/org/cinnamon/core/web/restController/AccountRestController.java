package org.cinnamon.core.web.restController;

import java.net.URI;

import javax.validation.Valid;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.exception.DuplateEmailException;
import org.cinnamon.core.exception.DuplateUserIdException;
import org.cinnamon.core.service.UserBaseService;
import org.cinnamon.core.vo.UserJoinVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author 신동성
 * @since 2016. 4. 11.
 */
@RestController("cinnamon.accountRestController")
@RequestMapping("/rest/accounts")
public class AccountRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserBaseService<UserBase> userService;
	
	/**
	 * 아이디 중복확인
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="{userId}", method=RequestMethod.HEAD)
	ResponseEntity<Void> headUser(@PathVariable String userId) {
		logger.info("start");
		
		if (userService.exists(userId)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * 회원가입
	 * 
	 * @param userVo
	 * @param builder
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<JoinFail> postUsers(@RequestBody @Valid UserJoinVo userVo, UriComponentsBuilder builder) {
		logger.info("start");
		
		UserBase user = null;
		try {
			user = userService.join(null, userVo);
		} catch (DuplateUserIdException e) {
			JoinFail joinFail = new JoinFail();
			joinFail.setCause("duplicateUserId");
			
			return ResponseEntity.badRequest().body(joinFail);
		} catch (DuplateEmailException e) {
			JoinFail joinFail = new JoinFail();
			joinFail.setCause("duplicateEmail");
			return ResponseEntity.badRequest().body(joinFail);
		}
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				UserBaseRestController.class,
				"getUser",
				user.getUserId())
			.build()
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * 비밀번호 변경
	 * 
	 * @return
	 */
	@RequestMapping(value="password", method=RequestMethod.PUT)
	void putPassword() {
		logger.info("start");
		
	}
	
	/**
	 * 
	 * @param userJoinVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="initPassword")
	Integer initPassword(@RequestBody UserJoinVo userJoinVo) throws Exception {
		return userService.initPassword(userJoinVo);
	}
}

/**
 * 
 * created at 2017. 1. 17.
 * @author shindongseong
 */
class JoinFail {
	String cause;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
}