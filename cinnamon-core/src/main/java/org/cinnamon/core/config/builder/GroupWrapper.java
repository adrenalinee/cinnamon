package org.cinnamon.core.config.builder;

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
	
	GroupWrapper parent;
	
	List<GroupWrapper> childGroupWrappers = new LinkedList<>();
	
	GroupWrapper(String name, Object groupId) {
		group = new Group();
		group.setName(name);
		group.setGroupId(groupId.toString());
	}
	
	public GroupWrapper description(String description) {
		group.setDescription(description);
		return this;
	}
	
	public GroupWrapper message(String message) {
		group.setMessage(message);
		return this;
	}
	
	public GroupWrapper addChildGroup(GroupWrapper... childWrappers) {
		Arrays.asList(childWrappers).forEach(childWrapper -> {
			childWrapper.parent = this;
			childGroupWrappers.add(childWrapper);
		});
//		childGroupWrappers.addAll(Arrays.asList(childWrappers));
		return this;
	}
}
