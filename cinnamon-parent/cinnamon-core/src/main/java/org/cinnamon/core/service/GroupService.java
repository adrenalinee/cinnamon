package org.cinnamon.core.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.cinnamon.core.domain.Group;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author 동성
 *
 */
@Service
public class GroupService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	GroupRepository groupRepository;
	
	public List<Group> childs(String groupId) {
		return groupRepository.childGroups(groupId, UseStatus.enable);
	}
	
	/**
	 * 
	 * @param groupId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Map<String, String> childsMap(String groupId) {
		logger.info("start");
		
		Map<String, String> groupsMap = new TreeMap<String, String>();
		List<Group> childGroups = groupRepository.childGroups(groupId, UseStatus.enable);
		childGroups.stream().forEach(group -> {
			groupsMap.put(group.getGroupId(), group.getName());
		});
		
		return groupsMap;
	}
	
	
	@Transactional(readOnly=true)
	public Group get(String groupId) {
		logger.info("start");
		
		return groupRepository.findById(groupId);
	}
	
}
