package org.cinnamon.core.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.UserAuthorityRepository;
import org.cinnamon.core.util.ListPage;
import org.cinnamon.core.util.PagingUtil;
import org.cinnamon.core.vo.search.AuthoritySearch;
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
	UserAuthorityRepository permissionRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	
	@Transactional(readOnly=true)
	public ListPage<Permission> search(AuthoritySearch permissionSearch, Pageable pageable) {
		logger.info("start");
		
		int size = pageable.getPageSize();
		
		Page<Permission> domains = permissionRepository.search(permissionSearch, pageable);
		PagingUtil paging = new PagingUtil(domains.getNumber() + 1, size, domains.getTotalElements());
		
		
		ListPage<Permission> domainPage = new ListPage<Permission>();
		domainPage.setContent(domains.getContent());
		domainPage.setPaging(paging);
		
		return domainPage;
	}
	
	
	@Transactional(readOnly=true)
	public Map<Long, PermissionMenu> getPermissionMenus(String authority, Long menuGroupId) {
		logger.info("start");
		
		List<PermissionMenu> permissionMenus = permissionRepository.find(authority, menuGroupId);
		
		Map<Long, PermissionMenu> permissionMenusMap = new LinkedHashMap<Long, PermissionMenu>();
		for (PermissionMenu pm: permissionMenus) {
			Menu menu = pm.getMenu();
			permissionMenusMap.put(menu.getMenuId(), pm);
			
		}
		
		return permissionMenusMap;
	}
	
}
