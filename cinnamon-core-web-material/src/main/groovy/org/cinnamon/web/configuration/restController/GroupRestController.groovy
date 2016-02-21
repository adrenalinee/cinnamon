package org.cinnamon.web.configuration.restController;

import org.cinnamon.core.domain.Group
import org.cinnamon.core.service.GroupService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 
 * @author shindongseong
 * @since 2016. 2. 21.
 */
@RestController
@RequestMapping("/rest/configuration/groups")
class GroupRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	GroupService groupService
	
	
	@RequestMapping(value="{groupId}/childs", method=RequestMethod.GET)
	public List<Group> childs(@PathVariable String groupId) {
		logger.info("start")
		
		groupService.childs(groupId)
	}
}
