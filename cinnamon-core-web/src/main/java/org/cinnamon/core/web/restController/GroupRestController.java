package org.cinnamon.core.web.restController;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.cinnamon.core.domain.Group;
import org.cinnamon.core.service.GroupService;
import org.cinnamon.core.vo.GroupVo;
import org.cinnamon.core.vo.search.GroupSearch;
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
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 21.
 */
@RestController
@RequestMapping("/rest/configuration/groups")
public class GroupRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GroupService groupService;
	
	/**
	 * 자식 코드 가져오기
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="{groupId}/childs", method=RequestMethod.GET)
	List<Group> childs(@PathVariable String groupId) {
		logger.info("start");
		return groupService.childs(groupId);
	}
	
	
	/**
	 * 코드 리스트 조회
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupSearch
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<Group>list(GroupSearch groupSearch, Pageable pageable) {
		logger.info("start");
		return groupService.search(groupSearch, pageable);
	}
	
	/**
	 * 코드 생성
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupVo
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> create(@Valid @RequestBody GroupVo groupVo , UriComponentsBuilder builder) {
		logger.info("start");
		Group group = groupService.create(groupVo);
		
		URI location = builder.path("/configuration/groups/{groupId}")
				.buildAndExpand(group.getGroupId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	/**
	 * 코드 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupVo
	 * @return
	 */
	@RequestMapping(value="{groupId}", method=RequestMethod.PUT)
	void modify(@Valid @RequestBody GroupVo groupVo) {
		logger.info("start");
		groupService.modify(groupVo);
	}
	
	/**
	 * 코드 정보 조회
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="{groupId}", method=RequestMethod.GET)
	Group view(@PathVariable String groupId) {
		logger.info("start");
		return groupService.get(groupId);
	}
	
	/**
	 * 하위 코드 생성
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param parentGroupId
	 * @param groupId
	 * @param groupVo
	 * @return
	 */
	@RequestMapping(value="{parentGroupId}/childs", method=RequestMethod.POST)
	void childCreate(@PathVariable String parentGroupId, @RequestBody GroupVo groupVo) {
		logger.info("start");
		groupService.childCreate(parentGroupId, groupVo);
	}
	
	/**
	 * 부모 코드 변경
	 * @author 정명성
	 * create date : 2016. 3. 11.
	 * @param parentGroupId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="{parentGroupId}/parent", method=RequestMethod.PUT)
	void parentModify(@PathVariable String parentGroupId, @RequestBody String groupId) {
		logger.info("start");
		groupService.modifyParent(parentGroupId, groupId);
	}
	
	
	@RequestMapping(value="{groupId}/childsMap", method=RequestMethod.GET)
	Map<String, String> childsMap(@PathVariable String groupId) {
		logger.info("start");
		
		return groupService.childsMap(groupId);
	}
}
