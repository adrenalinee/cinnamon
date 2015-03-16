package org.cinnamon.common.mapping.vo;

import java.util.List;

/**
 * 
 * @author 동성
 * @since 2015. 2. 27.
 */
public class ApiCategory {
	
	private String name;
	
	private String path;
	
	private String description;
	
	private List<ApiDefinition> apiDefs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ApiDefinition> getApiDefs() {
		return apiDefs;
	}

	public void setApiDefs(List<ApiDefinition> apiDefs) {
		this.apiDefs = apiDefs;
	}
	
	
	
}
