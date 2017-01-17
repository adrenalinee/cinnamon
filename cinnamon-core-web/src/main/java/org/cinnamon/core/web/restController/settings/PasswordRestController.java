package org.cinnamon.core.web.restController.settings;

import javax.validation.Valid;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.exception.DuplateEmailException;
import org.cinnamon.core.exception.IncorrectPasswordException;
import org.cinnamon.core.service.UserPasswordService;
import org.cinnamon.core.vo.UserPasswordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author shindongseong
 * @since 2016. 4. 13.
 */
@RestController
@RequestMapping("/rest/settings/password")
public class PasswordRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserPasswordService<UserBase> userPasswordService;
	
	
	@RequestMapping(value="change", method=RequestMethod.PUT)
	ResponseEntity<PasswordChangeFail> putMenuGroup(
		@AuthenticationPrincipal UserDetails userDetails,
		@RequestBody @Valid UserPasswordVo userPasswordVo) {
		logger.info("start");
		
		try {
			userPasswordService.change(userDetails.getUsername(), userPasswordVo);
		} catch (IncorrectPasswordException e) {
			PasswordChangeFail passwordChangeFail = new PasswordChangeFail();
			passwordChangeFail.setCause("incorrectPassword");
			return ResponseEntity.ok(passwordChangeFail);
		}
		
		return ResponseEntity.ok().build();
	}
}

/**
 * 
 * created at 2017. 1. 17.
 * @author shindongseong
 */
class PasswordChangeFail {
	private String cause;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
}

