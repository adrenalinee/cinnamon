package org.cinnamon.core.config.builder;

import java.util.LinkedList;
import java.util.List;

import org.cinnamon.core.domain.Site;

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
public class SiteWrapper {
	
	Site site;
	
	MenuGroupWrapper defaultMenuGroupWrapper;
	
	List<MenuGroupWrapper> menuGroupWrappers = new LinkedList<>();
	
	SiteWrapper(String name, String siteId) {
		site = new Site();
		site.setSiteId(siteId);
		site.setName(name);
	}
	
	public SiteWrapper description(String description) {
		site.setDescription(description);
		return this;
	}
	
	public SiteWrapper url(String url) {
		site.setUrl(url);
		return this;
	}
	
	public SiteWrapper indexPage(String indexPage) {
		site.setIndexPage(indexPage);
		return this;
	}
	
	public SiteWrapper addMenuGroup(MenuGroupWrapper menuGroupWrapper) {
		menuGroupWrappers.add(menuGroupWrapper);
		return this;
	}
	
	public SiteWrapper addMenuGroup(boolean isDefault, MenuGroupWrapper menuGroupWrapper) {
		menuGroupWrappers.add(menuGroupWrapper);
		
		if (isDefault) {
			defaultMenuGroupWrapper = menuGroupWrapper;
		}
		
		return this;
	}
	
}
