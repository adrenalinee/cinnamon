package org.cinnamon.apps.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
@Component
public class ApiClientDomainBuilder {
	
	@Autowired
	EntityManager em;
	
	List<ApplicationAuthorityWrapper> authorityWrappers = new LinkedList<>();
	
	List<ScopeWrapper> scopeWrappers = new LinkedList<>();
	
	List<ResourceWrapper> resourceWrappers = new LinkedList<>();
	
	List<ApplicationWrapper> applicationWrappers = new LinkedList<>();
	
	
	
	public ApiClientDomainBuilder addScopes(ScopeWrapper... scopeWrappers) {
		this.scopeWrappers.addAll(Arrays.asList(scopeWrappers));
		return this;
	}
	
	public ApiClientDomainBuilder addAuthorities(ApplicationAuthorityWrapper... authorityWrappers) {
		this.authorityWrappers.addAll(Arrays.asList(authorityWrappers));
		return this;
	}
	
	public ApiClientDomainBuilder addResources(ResourceWrapper resourceWrappers) {
		this.resourceWrappers.addAll(Arrays.asList(resourceWrappers));
		return this;
	}
	
	public ApiClientDomainBuilder addApplications(ApplicationWrapper applicationWrappers) {
		this.applicationWrappers.addAll(Arrays.asList(applicationWrappers));
		return this;
	}
	
	
	
	
	
	
	public static ClientWrapper client(String name, String clientId) {
		return new ClientWrapper(name, clientId);
	}
	
	public static ApplicationWrapper application(String name) {
		return new ApplicationWrapper(name);
	}
	
	public static ScopeWrapper scope(Object scope) {
		return new ScopeWrapper(scope.toString());
	}
	
	public static ResourceWrapper resource(String name, String resourceId) {
		return new ResourceWrapper(name, resourceId);
	}
	
	public static ApiWrapper api(String name, String url) {
		return new ApiWrapper(name, url);
	}
	
	public static ApiGroupWrapper apiGroup(String name) {
		return new ApiGroupWrapper(name);
	}
	
	public static ApplicationAuthorityWrapper authority(String name, Object authority) {
		return new ApplicationAuthorityWrapper(name, authority.toString());
	}

}
