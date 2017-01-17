package org.cinnamon.core.web.restController;

import java.net.URI;

import javax.validation.Valid;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.service.UserGroupService;
import org.cinnamon.core.vo.UserGroupVo;
import org.cinnamon.core.vo.search.UserGroupSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * @author shindongseong
 * @since 2015. 12. 25.
 */
@RestController
@RequestMapping("/rest/configuration/userGroups")
public class UserGroupRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserGroupService<UserBase> userGroupService;
	
	/**
	 * 
	 * @param userGroupVo
	 * @param builder
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postUserGroups(@RequestBody @Valid UserGroupVo userGroupVo, UriComponentsBuilder builder) {
		logger.info("String");
		
		UserGroup userGroup = userGroupService.save(userGroupVo);
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				UserGroupRestController.class,
				"getUserGroup",
				userGroup.getUserGroupId())
			.build()
			.toUri();
		
		return ResponseEntity
			.created(location)
			.build();
	}
	
	/**
	 * 
	 * @param userGroupSearch
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<UserGroup> getUserGroups(UserGroupSearch userGroupSearch, Pageable pageable) {
		logger.info("String");
		
		return userGroupService.getList(userGroupSearch, pageable);
	}
	
	/**
	 * 
	 * @param userGroupId
	 * @return
	 */
	@RequestMapping(value="{userGroupId}", method=RequestMethod.GET)
	UserGroup getUserGroup(@PathVariable Long userGroupId) {
		logger.info("String");
		
		return userGroupService.get(userGroupId);
	}
	
	/**
	 * 
	 * @param userGroupId
	 * @param userGroupVo
	 */
	@RequestMapping(value="{userGroupId}", method=RequestMethod.PUT)
	void putUserGroup(@PathVariable Long userGroupId, @RequestBody @Valid UserGroupVo userGroupVo) {
		logger.info("String");
		
		userGroupService.modify(userGroupId, userGroupVo);
	}
	
	/**
	 * 
	 * @param userGroupId
	 * @param permissionId
	 */
	@RequestMapping(value="{userGroupId}/permission/{permissionId}")
	void submitUserPermission(@PathVariable Long userGroupId, @PathVariable Long permissionId) {
		logger.info("start");
		
		userGroupService.submitUserGroupToPermission(userGroupId, permissionId);
	}
}
