package org.cinnamon.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author 동성
 *
 */
@Entity
@Table(name="groups")
@JsonInclude(Include.NON_EMPTY)
public class Group {
	
	@Id
	@Column(length=50)
	String groupId;
	
	@Column(nullable=false)
	String name;
	
//	@JsonIgnore
	@ManyToOne
	Group parent;
	
	
//	@OneToMany(mappedBy="parent")
//	@MapKeyColumn(name="groupId")
//	Map<String, Group> childsMap;
	
	Integer orders;
	
	String description;
	
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	//TODO 초기 데이터는 지워지거나 바뀌지 않도록 처리할 필드 필요함.
	
	
	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}

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

	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

//	public Map<String, Group> getChildsMap() {
//		return childsMap;
//	}
//
//	public void setChildsMap(Map<String, Group> childsMap) {
//		this.childsMap = childsMap;
//	}
	
	
}
