package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 3. 18.
 * @author 동성
 *
 */
public class PermissionSearch {
	Long permissionId;
	
	String name;
	
	String autority;
	
	String useStatus;

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutority() {
		return autority;
	}

	public void setAutority(String autority) {
		this.autority = autority;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
}