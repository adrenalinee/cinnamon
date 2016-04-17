package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.service.UserBaseService
import org.cinnamon.core.vo.UserJoinVo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

/**
 * 
 * @author 신동성
 * @since 2016. 4. 11.
 */
@RestController("cinnamon.accountRestController")
@RequestMapping("/rest/accounts")
class AccountRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	UserBaseService<UserBase> userService
	
	
	/**
	 * 아이디 중복확인
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="{userId}", method=RequestMethod.HEAD)
	def headUser(@PathVariable String userId) {
		logger.info("start")
		
		if (userService.exists(userId)) {
			ResponseEntity.ok().build()
		} else {
			ResponseEntity.notFound().build()
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
	def postUsers(@RequestBody @Valid UserJoinVo userVo, UriComponentsBuilder builder) {
		logger.info("start")
		
		UserBase user = userService.join(null, userVo)
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				UserBaseRestController.class,
				"getUser",
				user.getUserId())
			.build()
			.toUri()
		
		ResponseEntity.created(location).build()
	}
	
	
	/**
	 * 비밀번호 변경
	 * 
	 * @return
	 */
	@RequestMapping(value="password", method=RequestMethod.PUT)
	def putPassword() {
		logger.info("start")
		
		
	}
}
