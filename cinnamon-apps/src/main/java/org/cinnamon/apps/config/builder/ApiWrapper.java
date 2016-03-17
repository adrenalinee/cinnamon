package org.cinnamon.apps.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.apps.domain.ApiDefinition;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ApiWrapper {
	
	ApiDefinition api;
	
	List<String> permittedAuthorities = new LinkedList<>();
	
	List<String> permittedScopes = new LinkedList<>();
	
	ApiWrapper(String name, String url) {
		api = new ApiDefinition();
		api.setName(name);
		api.setUrl(url);
	}
	
	public ApiWrapper description(String description) {
		api.setDescription(description);
		return this;
	}
	
	public ApiWrapper addPermitedAuthorities(Object... authorities) {
		Arrays.asList(authorities).forEach(authority -> {
			permittedAuthorities.add(authority.toString());
		});
		return this;
	}
	
	public ApiWrapper addPermittedScopes(Object... scopes) {
		Arrays.asList(scopes).forEach(scope -> {
			permittedScopes.add(scope.toString());
		});
		return this;
	}
}
