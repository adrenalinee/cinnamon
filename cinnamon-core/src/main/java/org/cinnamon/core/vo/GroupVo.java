package org.cinnamon.core.vo;

import javax.persistence.Column;

import org.cinnamon.core.domain.enumeration.UseStatus;

public class GroupVo {

	String groupId;
	
	String name;
	
	/**
	 * 국제화 기능에서 사용할 메시지 키
	 */
	@Column(length=300)
	String message;
	
	Integer orders;
	
	String description;
	
	UseStatus useStatus = UseStatus.enable;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}
	
}
