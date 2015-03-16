package org.cinnamon.common.mapping.vo;

import java.util.List;

/**
 * 
 * @author 동성
 * @since 2015. 2. 27.
 */
public class ApiDefinition {
	
	private String method;
	
	private String url;
	
	private List<String> urlParameters;
	
	private List<String> queryParameters;
	
	private Object requestBody;
	
	private Object responseBody;
	
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getUrlParameters() {
		return urlParameters;
	}

	public void setUrlParameters(List<String> urlParameters) {
		this.urlParameters = urlParameters;
	}

	public List<String> getQueryParameters() {
		return queryParameters;
	}

	public void setQueryParameters(List<String> queryParameters) {
		this.queryParameters = queryParameters;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}
	
}
