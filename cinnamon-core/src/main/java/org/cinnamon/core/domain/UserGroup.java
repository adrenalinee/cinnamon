package org.cinnamon.core.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 운영자 그룹
 * 
 * @author 동성
 * @since 2014. 12. 22.
 */
@Entity
public class UserGroup {
	
	@Id
	@GeneratedValue
	Long userGroupId;
	
	@Column(nullable=false)
	String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy="userGroups")
//	@OneToMany(mappedBy="userGroup")
	List<UserBase> users;
	
	
	@ManyToOne
	UserAuthority role;
	
	/**
	 * 로그인후 처음으로 보여줄 페이지.
	 * 혹은 갈곳이 없을때 보여줄 페이지.
	 */
//	String defualtPage;
	
	String description;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}

	public UserAuthority getRole() {
		return role;
	}

	public void setRole(UserAuthority permission) {
		this.role = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public List<UserBase> getUsers() {
		return users;
	}

	public void setUsers(List<UserBase> users) {
		this.users = users;
	}

//	public String getDefualtPage() {
//		return defualtPage;
//	}
//
//	public void setDefualtPage(String defualtPage) {
//		this.defualtPage = defualtPage;
//	}
	
}
