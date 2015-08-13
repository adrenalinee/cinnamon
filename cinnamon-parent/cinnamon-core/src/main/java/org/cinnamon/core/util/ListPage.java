package org.cinnamon.core.util;

import java.util.Collection;

/**
 * 
 * @author dsshin
 * created date: 2014. 4. 18.
 * @param <T>
 */
public class ListPage<T> {
	
	private Collection<T> content;
	
	private PagingUtil paging;

	public PagingUtil getPaging() {
		return paging;
	}

	public void setPaging(PagingUtil paging) {
		this.paging = paging;
	}

	public Collection<T> getContent() {
		return content;
	}

	public void setContent(Collection<T> content) {
		this.content = content;
	}
	
}
