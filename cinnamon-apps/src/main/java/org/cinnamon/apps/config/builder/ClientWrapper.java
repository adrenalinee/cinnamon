package org.cinnamon.apps.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.apps.domain.Client;
import org.cinnamon.apps.domain.enumeration.AuthorizationGrantType;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ClientWrapper {
	
	Client client;
	
	List<String> permittedScopes = new LinkedList<>();
	
	List<String> permittedResources = new LinkedList<>();
	
	List<String> clientRedirectUris = new LinkedList<>();
	
	List<AuthorizationGrantType> authorizationGrantTypes = new LinkedList<>();
	
	
	ClientWrapper(String name, String clientId) {
		client = new Client();
		client.setName(name);
		client.setClientId(clientId);
	}
	
	public ClientWrapper secret(String secret) {
		client.setSecret(secret);
		return this;
	}
	
	public ClientWrapper description(String description) {
		client.setDescription(description);
		return this;
	}
	
	public ClientWrapper accessTokenValiditySeconds(int accessTokenValiditySeconds) {
		client.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
		return this;
	}
	
	public ClientWrapper refreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		client.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
		return this;
	}
	
	public ClientWrapper addPermittedScopes(Object... scopes) {
		Arrays.asList(scopes).forEach(scope -> {
			permittedScopes.add(scope.toString());
		});
		return this;
	}
	
	public ClientWrapper addPermittedResourceIds(Object ... resourcesIds) {
		Arrays.asList(resourcesIds).forEach(resourceId -> {
			permittedResources.add(resourceId.toString());
		});
		return this;
	}
	
	public ClientWrapper addRedirectUris(Object... uris) {
		Arrays.asList(uris).forEach(uri -> {
			clientRedirectUris.add(uri.toString());
		});
		return this;
	}
	
	public ClientWrapper addAuthorizedGrantTypes(AuthorizationGrantType... grantTypes) {
		authorizationGrantTypes.addAll(Arrays.asList(grantTypes));
		return this;
	}
	
	
}
