package org.cinnamon.users.web.restController.settings

import javax.validation.Valid

import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.exception.DuplateEmailException;
import org.cinnamon.core.exception.IncorrectPasswordException;
import org.cinnamon.core.service.UserPasswordService
import org.cinnamon.core.vo.UserPasswordVo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 
 * @author shindongseong
 * @since 2016. 4. 13.
 */
@RestController
@RequestMapping("/rest/settings/password")
class PasswordRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	UserPasswordService<UserBase> userPasswordService
	
	
	@RequestMapping(value="change", method=RequestMethod.PUT)
	def putMenuGroup(
		@AuthenticationPrincipal UserDetails userDetails,
		@RequestBody @Valid UserPasswordVo userPasswordVo) {
		logger.info("start")
		
		try {
			userPasswordService.change(userDetails.getUsername(), userPasswordVo);
		} catch (IncorrectPasswordException e) {
			return ResponseEntity.badRequest().body([
				"cause": "incorrectPassword"
			])
		}
		
		return ResponseEntity.ok().build();
	}
}
