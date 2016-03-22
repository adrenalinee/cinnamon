package org.cinnamon.core.config.builder;

import java.util.LinkedHashMap;
import java.util.Map;

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
	
//	List<MenuGroupWrapper> menuGroupWrappers = new LinkedList<>();
	
	Map<String, MenuGroupWrapper> menuGroupWrappers = new LinkedHashMap<>();
	
	
	
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
//		menuGroupWrappers.add(menuGroupWrapper);
		menuGroupWrappers.put(menuGroupWrapper.menuGroup.getDimension(), menuGroupWrapper);
		return this;
	}
	
	public SiteWrapper addMenuGroup(boolean isDefault, MenuGroupWrapper menuGroupWrapper) {
//		menuGroupWrappers.add(menuGroupWrapper);
		menuGroupWrappers.put(menuGroupWrapper.menuGroup.getDimension(), menuGroupWrapper);
		
		if (isDefault) {
			defaultMenuGroupWrapper = menuGroupWrapper;
		}
		
		return this;
	}
	
	public MenuGroupWrapper menuGroup(String dimension) {
		return menuGroupWrappers.get(dimension);
	}
	
}
