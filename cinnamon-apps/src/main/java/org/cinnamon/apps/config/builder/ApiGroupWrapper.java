package org.cinnamon.apps.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.apps.domain.ApiGroup;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ApiGroupWrapper {
	
	ApiGroup apiGroup;
	
	List<ApiWrapper> apiWrappers = new LinkedList<>();
	
	ApiGroupWrapper(String name) {
		apiGroup = new ApiGroup();
		apiGroup.setName(name);
	}
	
	public ApiGroupWrapper description(String description) {
		apiGroup.setDescription(description);
		return this;
	}
	
	public ApiGroupWrapper addApis(ApiWrapper... apiWrappers) {
		this.apiWrappers.addAll(Arrays.asList(apiWrappers));
		return this;
	}
	
}
