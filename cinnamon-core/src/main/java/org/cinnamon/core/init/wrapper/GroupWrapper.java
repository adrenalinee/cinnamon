package org.cinnamon.core.init.wrapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Group;

/**
 * 
 *
 * created date: 2015. 9. 15.
 * @author 신동성
 */
public class GroupWrapper {
	
	Group group;
	
	List<GroupWrapper> childGroupWrappers = new LinkedList<>();
	
	GroupWrapper(String name, String groupId) {
		group = new Group();
		group.setName(name);
		group.setGroupId(groupId);
	}
	
	public GroupWrapper description(String description) {
		group.setDescription(description);
		return this;
	}
	
	public GroupWrapper message(String message) {
		group.setMessage(message);
		return this;
	}
	
	GroupWrapper addChildGroup(GroupWrapper... childWrappers) {
		childGroupWrappers.addAll(Arrays.asList(childWrappers));
		return this;
	}
}
