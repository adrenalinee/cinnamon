package org.cinnamon.core.web.restController;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.service.UserBaseService;
import org.cinnamon.core.service.UserGroupService;
import org.cinnamon.core.vo.UserBaseVo;
import org.cinnamon.core.vo.UserJoinVo;
import org.cinnamon.core.vo.search.UserBaseSearch;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 *
 * created date: 2015. 9. 9.
 * @author 신동성
 */
@RestController
@RequestMapping("/rest/configuration/users")
class UserBaseRestController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserBaseService<UserBase> userService;
	
	@Autowired
	UserGroupService<UserBase> userGroupService;
	
	@Autowired
	Mapper beanMapper;
	
	/**
	 * 
	 * @param userDetails
	 * @param userVo
	 * @param builder
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postUsers(
		@AuthenticationPrincipal UserDetails userDetails,
		@RequestBody @Valid UserJoinVo userVo, UriComponentsBuilder builder) {
		logger.info("start");
		
		String creator = userDetails.getUsername();
		UserBase user = userService.join(creator, userVo);
		
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
	 * 
	 * @param userSearch
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<UserBase> getUsers(UserBaseSearch userSearch, Pageable pageable) {
		logger.info("start");
		
		return userService.getList(userSearch, pageable);
	}
	
	/**
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
	
	
	@RequestMapping(value="{userId}", method=RequestMethod.GET)
	UserBase getUser(@PathVariable String userId) {
		logger.info("start");
		
		return userService.get(userId);
	}
	
	/**
	 * 
	 * @param userId
	 * @param userVo
	 */
	@RequestMapping(value="{userId}", method=RequestMethod.PUT)
	void putUser(@PathVariable String userId, @RequestBody UserBaseVo userVo) {
		logger.info("start");
		
		userService.save(userId, userVo);
	}
	
	/**
	 * 
	 * @param userId
	 * @param userGroupInfo
	 */
	@RequestMapping(value="{userId}/userGroups", method=RequestMethod.PUT)
	void putUserGroups(@PathVariable String userId, @RequestBody @Valid UserGroupInfo userGroupInfo) {
		logger.info("start");
		
		userGroupService.addMember(userGroupInfo.getUserGroupId(), userId);
	}
}


/**
 * 
 * @author 신동성
 * @since 2016. 2. 12.
 */
class UserGroupInfo {
	
	@NotNull
	Long userGroupId;

	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}
}