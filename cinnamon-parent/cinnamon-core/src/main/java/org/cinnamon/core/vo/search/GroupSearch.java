package org.cinnamon.core.vo.search;

/**
 * 
 * @author 동성
 * @since 2015. 1. 27.
 */
public class GroupSearch {
	
	String name;
	
	String groupId;
	
	String parent;
	
//	String child;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

//	public String getChild() {
//		return child;
//	}
//
//	public void setChild(String child) {
//		this.child = child;
//	}
	
}
