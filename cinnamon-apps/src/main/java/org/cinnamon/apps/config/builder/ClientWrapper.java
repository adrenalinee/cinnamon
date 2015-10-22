package org.cinnamon.apps.config.builder;

import org.cinnamon.apps.domain.Client;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ClientWrapper {
	
	Client client;
	
	
	ClientWrapper(String name, String clientId) {
		client = new Client();
		client.setName(name);
		client.setClientId(clientId);
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
	
	public ClientWrapper addScopes(Object... scopes) {
		
		return this;
	}
	
	public ClientWrapper addResourceIds(String ... resourcesIds) {
		
		return this;
	}
	
	public ClientWrapper addRedirectUris(String... uris) {
		
		return this;
	}
	
	public ClientWrapper addAuthorizedGrantTypes(Object... grantTypes) {
		
		return this;
	}
	
	
}
