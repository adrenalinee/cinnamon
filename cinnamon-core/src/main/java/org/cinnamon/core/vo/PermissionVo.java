package org.cinnamon.core.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.cinnamon.core.domain.enumeration.UseStatus;
import org.hibernate.validator.constraints.NotEmpty;

public class PermissionVo {

	Long permissionId;
	
	@NotEmpty
	@NotNull
	@Size(max=50)
	String authority;
	
	@NotEmpty
	@NotNull
	@Size(max=200)
	String name;
	
	UseStatus useStatus = UseStatus.enable;
	
	@Size(max=4000)
	String description;

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
