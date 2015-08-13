package org.cinnamon.core.domain.embeddable;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author 동성
 * @since 2015. 2. 12.
 */
@Embeddable
public class ActivityParam {
	
	String uri;
	
	Serializable id;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}
	
}
