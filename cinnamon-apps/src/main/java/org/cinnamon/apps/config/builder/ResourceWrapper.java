package org.cinnamon.apps.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.apps.domain.Resource;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ResourceWrapper {
	
	Resource resource;
	
	List<ApiGroupWrapper> apiGroupWrappers = new LinkedList<>();
	
	
	ResourceWrapper(String name, String resourceId) {
		resource = new Resource();
		resource.setName(name);
		resource.setResourceId(resourceId);
	}
	
	public ResourceWrapper description(String description) {
		resource.setDescription(description);
		return this;
	}
	
	public ResourceWrapper addApiGroups(ApiGroupWrapper... apiGroupWrappers) {
		this.apiGroupWrappers.addAll(Arrays.asList(apiGroupWrappers));
		return this;
	}
}
