package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 3. 18.
 * @author 동성
 *
 */
public class AuthoritySearch {
	
	String keyword;
	
	String name;
	
	String autority;
	
	String useStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutority() {
		return autority;
	}

	public void setAutority(String autority) {
		this.autority = autority;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
