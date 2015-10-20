package org.cinnamon.core.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.UserAuthority;
import org.cinnamon.core.domain.MenuAuthority;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.RoleRepository;
import org.cinnamon.core.util.ListPage;
import org.cinnamon.core.util.PagingUtil;
import org.cinnamon.core.vo.search.RoleSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * create date: 2015. 5. 6.
 * @author 신동성
 *
 */
@Service
public class RoleService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	RoleRepository permissionRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	
	@Transactional(readOnly=true)
	public ListPage<UserAuthority> search(RoleSearch permissionSearch, Pageable pageable) {
		logger.info("start");
		
		int size = pageable.getPageSize();
		
		Page<UserAuthority> domains = permissionRepository.search(permissionSearch, pageable);
		PagingUtil paging = new PagingUtil(domains.getNumber() + 1, size, domains.getTotalElements());
		
		
		ListPage<UserAuthority> domainPage = new ListPage<UserAuthority>();
		domainPage.setContent(domains.getContent());
		domainPage.setPaging(paging);
		
		return domainPage;
	}
	
	
	@Transactional(readOnly=true)
	public Map<Long, MenuAuthority> getPermissionMenus(String authority, Long menuGroupId) {
		logger.info("start");
		
		List<MenuAuthority> permissionMenus = permissionRepository.find(authority, menuGroupId);
		
		Map<Long, MenuAuthority> permissionMenusMap = new LinkedHashMap<Long, MenuAuthority>();
		for (MenuAuthority pm: permissionMenus) {
			Menu menu = pm.getMenu();
			permissionMenusMap.put(menu.getMenuId(), pm);
			
		}
		
		return permissionMenusMap;
	}
	
}
