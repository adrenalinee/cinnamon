package org.cinnamon.web.configuration.restController

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.service.UserBaseService
import org.cinnamon.core.service.UserGroupService;
import org.cinnamon.core.vo.search.UserBaseSearch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 
 *
 * created date: 2015. 9. 9.
 * @author 신동성
 */
@RestController
@RequestMapping("/rest/configuration/users")
class UserBaseRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	UserBaseService<UserBase> userService
	
	@Autowired
	UserGroupService<UserBase> userGroupService
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<UserBase> users(UserBaseSearch userSearch, Pageable pageable) {
		logger.info("String")
		
		return userService.getList(userSearch, pageable)
	}
	
	
	@RequestMapping(value="{userId}", method=RequestMethod.GET)
	UserBase users(@PathVariable String userId) {
		logger.info("String")
		
		return userService.get(userId)
	}
	
	
	@RequestMapping(value="{userId}/userGroups", method=RequestMethod.PUT)
	def userPutUserGroups(@PathVariable String userId, @RequestBody @Valid UserGroupInfo userGroupInfo) {
		logger.info("String")
		
		userGroupService.addMember(userGroupInfo.getUserGroupId(), userId)
	}
}


/**
 * 
 * @author 신동성
 * @since 2016. 2. 12.
 */
class UserGroupInfo {
	
	@NotNull
	Long userGroupId
}