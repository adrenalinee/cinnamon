package org.cinnamon.apps.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.apps.domain.ApiDefinition;
import org.cinnamon.apps.domain.ApiGroup;
import org.cinnamon.apps.domain.Application;
import org.cinnamon.apps.domain.ApplicationAuthority;
import org.cinnamon.apps.domain.Client;
import org.cinnamon.apps.domain.ClientAuthorizedGrantType;
import org.cinnamon.apps.domain.ClientRedirectUri;
import org.cinnamon.apps.domain.Resource;
import org.cinnamon.apps.domain.Scope;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
//@Component
public class ApiClientDomainBuilder {
	
//	@Autowired
	EntityManager em;
	
	List<ApplicationAuthorityWrapper> authorityWrappers = new LinkedList<>();
	
	List<ScopeWrapper> scopeWrappers = new LinkedList<>();
	
	List<ResourceWrapper> resourceWrappers = new LinkedList<>();
	
	List<ApplicationWrapper> applicationWrappers = new LinkedList<>();
	
	
	public ApiClientDomainBuilder(EntityManager em) {
		this.em = em;
	}
	
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
	
	public static ResourceWrapper resource(String name, Object resourceId) {
		return new ResourceWrapper(name, resourceId.toString());
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

	public void build() {
		buildScopes();
		buildAuthorities();
		buildResources();
		buildApplications();
	}
	
	
	private void buildScopes() {
		scopeWrappers.forEach(scopeWrapper -> {
			Scope scope = scopeWrapper.scope;
			em.persist(scope);
		});
	}
	
	private void buildAuthorities() {
		authorityWrappers.forEach(authorityWrapper -> {
			ApplicationAuthority authority = authorityWrapper.authority;
			em.persist(authority);
		});
	}
	
	private void buildResources() {
		resourceWrappers.forEach(resourceWrapper -> {
			Resource resource = resourceWrapper.resource;
			em.persist(resource);
			
			resourceWrapper.apiGroupWrappers.forEach(apiGroupWrapper -> {
				ApiGroup apiGroup = apiGroupWrapper.apiGroup;
				apiGroup.setResource(resource);
				em.persist(apiGroup);
				
				apiGroupWrapper.apiWrappers.forEach(apiWrapper -> {
					ApiDefinition api = apiWrapper.api;
					
					List<ApplicationAuthority> authorities = createAuthorities(apiWrapper);
					api.setAuthorities(authorities);
					
					List<Scope> scopes = createScopes(apiWrapper);
					api.setPermittedScopes(scopes);
					
					em.persist(api);
				});
			});
		});
	}
	
	private List<Scope> createScopes(ApiWrapper apiWrapper) {
		List<Scope> scopes = new LinkedList<>();
		
		apiWrapper.permittedScopes.forEach(scopeString -> {
			for (ScopeWrapper scopeWrapper: scopeWrappers) {
				Scope scope = scopeWrapper.scope;
				if (scope.getScope().equals(scopeString)) {
					scopes.add(scope);
					break;
				}
			}
		});
		
		return scopes;
	}

	private List<ApplicationAuthority> createAuthorities(ApiWrapper apiWrapper) {
		List<ApplicationAuthority> authorities = new LinkedList<>();
		
		apiWrapper.permittedAuthorities.forEach(authorityString -> {
			for (ApplicationAuthorityWrapper authorityWrapper: authorityWrappers) {
				ApplicationAuthority applicationAuthority = authorityWrapper.authority;
				if (applicationAuthority.getAuthority().equals(authorityString)) {
					authorities.add(applicationAuthority);
					break;
				}
			}
		});
		
		return authorities;
	}
	
	private void buildApplications() {
		applicationWrappers.forEach(applicationWrapper -> {
			Application app = applicationWrapper.app;
			em.persist(app);
			
			applicationWrapper.authorities.forEach(authorityString -> {
				app.addAuthority(getAuthority(authorityString));
			});
			
			applicationWrapper.clientWrappers.forEach(clientWrapper -> {
				Client client = clientWrapper.client;
				client.setApplication(app);
				
				List<ClientRedirectUri> redirectUris = createClientRedirectUris(clientWrapper);
				client.setRedirectUris(redirectUris);
				
				List<ClientAuthorizedGrantType> authorizedGrantTypes = createClientAuthorizedGrantTypes(clientWrapper);
				client.setAuthorizedGrantTypes(authorizedGrantTypes);
				
				em.persist(client);
				
				clientWrapper.permittedScopes.forEach(scopeString -> {
					client.addPermittedScope(getScope(scopeString));
				});
				
				clientWrapper.permittedResources.forEach(resourceId -> {
					client.addPermittedResource(getResource(resourceId));
				});
			});
		});
	}
	
	
	private Resource getResource(String resourceId) {
		for (ResourceWrapper resourceWrapper: resourceWrappers) {
			Resource resource = resourceWrapper.resource;
			if (resource.getResourceId().equals(resourceId)) {
				return resource;
			}
		}
		
		throw new RuntimeException("resource가 정의 되어 있지 않습니다. resourceId: " + resourceId);
	}
	
	
	private ApplicationAuthority getAuthority(String authorityString) {
		for (ApplicationAuthorityWrapper authorityWrapper: authorityWrappers) {
			ApplicationAuthority authority = authorityWrapper.authority;
			if (authority.getAuthority().equals(authorityString)) {
				return authority;
			}
		}
		
		throw new RuntimeException("authority가 정의 되어 있지 않습니다. authority: " + authorityString);
	}
	
	
	private Scope getScope(String scopeString) {
		for (ScopeWrapper scopeWrapper: scopeWrappers) {
			Scope scope = scopeWrapper.scope;
			if (scope.getScope().equals(scopeString)) {
				return scope;
			}
		}
		
		throw new RuntimeException("scope가 정의 되어 있지 않습니다. scope: " + scopeString);
	}
	
	private List<ClientRedirectUri> createClientRedirectUris(ClientWrapper clientWrapper) {
		List<ClientRedirectUri> clientRedirectUris = new LinkedList<>();
		
		Client client = clientWrapper.client;
		clientWrapper.clientRedirectUris.forEach(clientRedirectString -> {
			ClientRedirectUri clientRedirectUri = new ClientRedirectUri();
			clientRedirectUri.setClient(client);
			clientRedirectUri.setUri(clientRedirectString);
			
			clientRedirectUris.add(clientRedirectUri);
		});
		
		return clientRedirectUris;
	}
	
	private List<ClientAuthorizedGrantType> createClientAuthorizedGrantTypes(ClientWrapper clientWrapper) {
		List<ClientAuthorizedGrantType> authorizedGrantTypes = new LinkedList<>();
		
		Client client = clientWrapper.client;
		clientWrapper.authorizationGrantTypes.forEach(authorizationGrantType -> {
			ClientAuthorizedGrantType clientAuthorizedGrantType = new ClientAuthorizedGrantType(authorizationGrantType);
			clientAuthorizedGrantType.setClient(client);
			
			authorizedGrantTypes.add(clientAuthorizedGrantType);
		});
		
		return authorizedGrantTypes;
	}
	
}
