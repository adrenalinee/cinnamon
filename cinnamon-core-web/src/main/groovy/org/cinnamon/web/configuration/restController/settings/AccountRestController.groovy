package org.cinnamon.web.configuration.restController.settings

import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.service.UserBaseService
import org.cinnamon.core.vo.UserBaseVo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 
 * @author 신동성
 * @since 2016. 6. 16.
 */
@RestController("cinnamon.settings.accountRestController")
@RequestMapping("/rest/settings/account")
class AccountRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	UserBaseService<UserBase> userService
	
	
	@RequestMapping(value="/me", method=RequestMethod.GET)
	UserBase getMe(@AuthenticationPrincipal UserDetails userDetails) {
		logger.info("start")
		
		return userService.get(userDetails.getUsername());
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	def putMe(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserBaseVo userVo) {
		logger.info("start")
		
		String userId = userDetails.getUsername()
		
		userService.save(userId, userVo)
	}
}
