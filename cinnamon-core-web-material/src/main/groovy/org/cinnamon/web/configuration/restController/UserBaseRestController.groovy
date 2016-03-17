package org.cinnamon.web.configuration.restController

import javax.validation.Valid
import javax.validation.constraints.NotNull

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.service.UserBaseService
import org.cinnamon.core.service.UserGroupService
import org.cinnamon.core.vo.UserBaseVo
import org.cinnamon.core.vo.UserJoinVo
import org.cinnamon.core.vo.search.UserBaseSearch
import org.dozer.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

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
	
	@Autowired
	Mapper beanMapper
	
	@RequestMapping(value="", method=RequestMethod.POST)
	def postUsers(@RequestBody @Valid UserJoinVo userVo, UriComponentsBuilder builder) {
		logger.info("start")
		
		UserBase user = userService.join(userVo)
		
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
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<UserBase> getUsers(UserBaseSearch userSearch, Pageable pageable) {
		logger.info("start")
		
		println ToStringBuilder.reflectionToString(userSearch)
		
		userService.getList(userSearch, pageable)
	}
	
	
	@RequestMapping(value="{userId}", method=RequestMethod.HEAD)
	def headUser(@PathVariable String userId) {
		logger.info("start")
		
		if (userService.exists(userId)) {
			ResponseEntity.ok().build()
		} else {
			ResponseEntity.notFound().build()
		}
	}
	
	
	@RequestMapping(value="{userId}", method=RequestMethod.GET)
	UserBase getUser(@PathVariable String userId) {
		logger.info("start")
		
		userService.get(userId)
	}
	
	
	@RequestMapping(value="{userId}", method=RequestMethod.PUT)
	def putUser(@PathVariable String userId, @RequestBody UserBaseVo userVo) {
		logger.info("start")
		
		userService.save(userId, userVo)
	}
	
	
	@RequestMapping(value="{userId}/userGroups", method=RequestMethod.PUT)
	def putUserGroups(@PathVariable String userId, @RequestBody @Valid UserGroupInfo userGroupInfo) {
		logger.info("start")
		
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