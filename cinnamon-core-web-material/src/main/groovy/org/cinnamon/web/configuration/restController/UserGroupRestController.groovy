package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.domain.UserGroup
import org.cinnamon.core.service.UserGroupService
import org.cinnamon.core.vo.search.UserGroupSearch
import org.cinnamon.web.configuration.vo.UserGroupVo
import org.dozer.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder

/**
 * 
 * @author shindongseong
 * @since 2015. 12. 25.
 */
@RestController
@RequestMapping("/rest/configuration/userGroups")
class UserGroupRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	UserGroupService<UserBase> userGroupService
	
	@Autowired
	Mapper beanMapper
	
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postUserGroups(@RequestBody @Valid UserGroupVo userGroupVo, UriComponentsBuilder builder) {
		logger.info("String")
		
		UserGroup userGroup = userGroupService.save(beanMapper.map(userGroupVo, UserGroup.class))
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				UserGroupRestController.class,
				"getUserGroup",
				userGroup.getUserGroupId())
			.build()
			.toUri()
		
		println location
		
		return ResponseEntity
			.created(location)
			.build()
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<UserGroup> getUserGroups(UserGroupSearch userGroupSearch, Pageable pageable) {
		logger.info("String")
		
		println pageable.getSort()
		
		return userGroupService.getList(userGroupSearch, pageable)
	}
	
	
	@RequestMapping(value="{userGroupId}", method=RequestMethod.GET)
	UserGroup getUserGroup(@PathVariable Long userGroupId) {
		logger.info("String")
		
		return userGroupService.get(userGroupId)
	}
}
