package org.cinnamon.apps.config;

import org.cinnamon.apps.config.builder.ApiClientDomainBuilder;

/**
 * 
 *
 * created date: 2015. 10. 22.
 * @author 신동성
 */
public interface ApiClientConfigurer {
	
	void configure(ApiClientDomainBuilder apiClientBuilder);
}
