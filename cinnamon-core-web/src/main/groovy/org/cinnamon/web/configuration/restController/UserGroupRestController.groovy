package org.cinnamon.web.configuration.restController

import org.cinnamon.core.domain.UserBase
import org.cinnamon.core.service.UserBaseService
import org.cinnamon.core.service.UserGroupService
import org.cinnamon.core.vo.search.UserGroupSearch;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController

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
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<UserBase> userGroups(UserGroupSearch userGroupSearch, Pageable pageable) {
		logger.info("String")
		
		println pageable.getSort()
		
		return userGroupService.getList(userGroupSearch, pageable)
	}
	
	
	@RequestMapping(value="{userId}", method=RequestMethod.GET)
	UserBase users(@PathVariable String userId) {
		logger.info("String")
		
		return userService.get(userId)
	}
}
