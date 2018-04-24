package org.cinnamon.core.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.cinnamon.core.domain.Group;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.exception.InvalidParameterException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.GroupRepository;
import org.cinnamon.core.vo.GroupVo;
import org.cinnamon.core.vo.search.GroupSearch;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Autowired
	Mapper mapper;
	
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
		
		return groupRepository.findById(groupId).get();
	}
	
	
	/**
	 * 코드 검색 (리스트 전체)
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupSearch
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<Group> search(GroupSearch groupSearch, Pageable pageable) {
		logger.info("start");
		return groupRepository.search(groupSearch, pageable);
	}
	
	/**
	 * 코드 생성
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupVo
	 * @return
	 */
	@Transactional
	public Group create(GroupVo groupVo) {
		logger.info("start");
		
		Group group = groupRepository.findById(groupVo.getGroupId()).get();
		if(group != null) {
			throw new InvalidParameterException("이미 등록되어 있는 코드 아이디 입니다. groupId : " + groupVo.getGroupId());
		}
		group = mapper.map(groupVo, Group.class);
		// 생성
		return groupRepository.save(group);
	}
	
	/**
	 * 코드 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param groupVo
	 */
	@Transactional
	public void modify(GroupVo groupVo) {
		logger.info("start");
		
		Group group = groupRepository.findById(groupVo.getGroupId()).get();
		if(group == null) {
			throw new NotFoundException("코드를 찾을 수 없습니다. groupId : " + groupVo.getGroupId());
		}
		
		mapper.map(groupVo, group);
	}
	
	/**
	 * 하위 코드 생성
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param parentGroupId
	 * @param groupVo
	 */
	@Transactional
	public void childCreate(String parentGroupId, GroupVo groupVo) {
		logger.info("start");
		Group group = groupRepository.findById(groupVo.getGroupId()).get();
		if(group != null) {
			throw new InvalidParameterException("이미 등록된 GroupId 입니다. groupId : " + groupVo.getGroupId());
		}
		group = mapper.map(groupVo, Group.class);
		Group parent = groupRepository.findById(parentGroupId).get();
		if(parent == null) {
			throw new NotFoundException("부모 코드가 존재하지 않습니다. parentGroupId : " + parentGroupId);
		}
		group.setParent(parent);
		groupRepository.save(group);
		
	}
	
	/**
	 * 부모 코드 변경
	 * @author 정명성
	 * create date : 2016. 3. 11.
	 * @param parentGroupId
	 * @param groupId
	 */
	@Transactional
	public void modifyParent(String parentGroupId, String groupId) {
		logger.info("start");
		Group parent = groupRepository.findById(parentGroupId).get();
		if(parent == null) {
			throw new NotFoundException("부모 코드가 존재하지 않습니다. parentGroupId : " + parentGroupId);
		}
		
		Group group = groupRepository.findById(groupId).get();
		if(group == null) {
			throw new NotFoundException("그룹 코드가 존재하지 않습니다. groupId : " + groupId);
		}
		
		group.setParent(parent);
	}
}
