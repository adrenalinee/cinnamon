package org.cinnamon.common.mapping.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author 동성
 * @since 2015. 2. 27.
 */
@JsonInclude(Include.NON_NULL)
public class ApiDefinition {
	
	private String method;
	
	private String url;
	
	private String description;
	
	private List<NameValuePair> headers;
	
	private List<NameValuePair> urlParameters;
	
	private List<NameValuePair> queryParameters;
	
	private Object requestBody;
	
	private Object responseBody;
	
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<NameValuePair> getUrlParameters() {
		return urlParameters;
	}

	public void setUrlParameters(List<NameValuePair> urlParameters) {
		this.urlParameters = urlParameters;
	}

	public List<NameValuePair> getQueryParameters() {
		return queryParameters;
	}

	public void setQueryParameters(List<NameValuePair> queryParameters) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<NameValuePair> getHeaders() {
		return headers;
	}

	public void setHeaders(List<NameValuePair> headers) {
		this.headers = headers;
	}
	
}
