package org.cinnamon.core.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.UserAuthorityRepository;
import org.cinnamon.core.util.ListPage;
import org.cinnamon.core.util.PagingUtil;
import org.cinnamon.core.vo.PermissionMenuVo;
import org.cinnamon.core.vo.PermissionVo;
import org.cinnamon.core.vo.search.AuthoritySearch;
import org.dozer.Mapper;
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
	MenuGroupRepository menuGroupRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	Mapper beanMapper;
	
	@Transactional(readOnly=true)
	public Page<Permission> search(AuthoritySearch permissionSearch, Pageable pageable) {
		logger.info("start");
		
		int size = pageable.getPageSize();
		
		Page<Permission> domains = permissionRepository.search(permissionSearch, pageable);
		
		PagingUtil paging = new PagingUtil(domains.getNumber() + 1, size, domains.getTotalElements());
		
		ListPage<Permission> domainPage = new ListPage<Permission>();
		domainPage.setContent(domains.getContent());
		domainPage.setPaging(paging);
		
		/*
		return domainPage;
		*/
		return domains;
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
	
	/**
	 * 메뉴 권한 정보 가져오기 추가
	 * @author 정명성
	 * create date : 2016. 3. 4.
	 * @param authority
	 * @param menuGroupId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Map<Long, PermissionMenu> getPermissionMenus(Long permissionMenuId, Long menuGroupId) {
		logger.info("start");
		
		List<PermissionMenu> permissionMenus = permissionRepository.find(permissionMenuId, menuGroupId);
		
		Map<Long, PermissionMenu> permissionMenusMap = new LinkedHashMap<Long, PermissionMenu>();
		for (PermissionMenu pm: permissionMenus) {
			Menu menu = pm.getMenu();
			permissionMenusMap.put(menu.getMenuId(), pm);
			
		}
		
		return permissionMenusMap;
	}	
	
	/**
	 * 권한 정보 가져오기
	 * @author 정명성
	 * create date : 2016. 3. 3.
	 * @param permissionId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Permission getPermission(Long permissionId) {
		logger.info("start");
		Permission permission = permissionRepository.findOne(permissionId);
		if(permission == null) {
			throw new NotFoundException("권한 정보가 존재하지 않습니다. permissionId :" + permissionId);
		}
		return permission;
	}
	
	/**
	 * 권한 정보 생성
	 * @author 정명성
	 * create date : 2016. 3. 3.
	 * @param permissionVo
	 */
	@Transactional
	public Permission createPermission(PermissionVo permissionVo) {
		logger.info("start");
		Permission permission = beanMapper.map(permissionVo, Permission.class);
		return permissionRepository.save(permission);
	}
	
	/**
	 * 권한 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 3.
	 * @param permissionVo
	 */
	@Transactional
	public void modifyPermission(Long permissionId, PermissionVo permissionVo) {
		logger.info("start");
		Permission permission = permissionRepository.findOne(permissionId);
		if(permission == null) {
			throw new NotFoundException("권한 정보가 존재하지 않습니다. permissionId :" + permissionId);
		}
		beanMapper.map(permissionVo, permission);
	}
	
	/**
	 * 메뉴 권한 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 7.
	 * @param permissionId
	 * @param permissionMenuVo
	 * @param menuGroupId
	 */
	@Transactional
	public void modifyMenu(Long permissionId, List<PermissionMenuVo> permissionMenuVo , Long menuGroupId) {
		logger.info("start");
		// 메뉴 권한 리스트 가져오기
		Permission permission = permissionRepository.findOne(permissionId);
		
		// 기존 권한 가져온 뒤 퍼미션 메뉴 리스트를 입력하여 수정한다.

		// 기존 메뉴 정보 불러오기
		List<PermissionMenu> permissionMenus = permissionRepository.find(permissionId, menuGroupId);
		// 기존 메뉴 정보 삭제
		permissionMenus = null;
		
		// 변경 메뉴 정보 입력
		for(PermissionMenuVo pm : permissionMenuVo){
			PermissionMenu permissionMenu = new PermissionMenu();
			Menu menu = new Menu();
			menu.setMenuId(pm.getMenuId());
			permissionMenu.setMenu(menu);
			permissionMenu.setPermitRoot(pm.isPermitRoot());
			permissionMenu.setPermitElse(pm.isPermitElse());
			permissionMenu.setPermission(permission);
			
			permissionRepository.savePermissionMenu(permissionMenu);
		}
		
	}
}
