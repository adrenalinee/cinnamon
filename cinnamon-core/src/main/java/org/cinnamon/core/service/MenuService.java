package org.cinnamon.core.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.exception.InvalidEntityException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.vo.MenuVo;
import org.cinnamon.core.vo.search.MenuSearch;
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
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
@Service
public class MenuService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MenuGroupRepository menuGroupRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	
	@Autowired
	Mapper beanMapper;
	
	
	@Transactional(readOnly=true)
	public Page<Menu> getList(MenuSearch menuSearch, Pageable pageable) {
		logger.info("start");
		
		System.out.println(ToStringBuilder.reflectionToString(menuSearch));
		
		return menuRepository.find(menuSearch, pageable);
	}
	
	
	@Transactional(readOnly=true)
	public Menu get(Long menuId) {
		logger.info("start");
		
		return menuRepository.findOne(menuId);
	}
	
	
	
	@Transactional(readOnly=true)
	public List<Menu> getList(String dimension, MenuPosition position , List<String> grantedAuthorities) {
		logger.info("start");
		
		System.out.println(dimension);
		System.out.println(position);
		grantedAuthorities.forEach(ga -> {
			System.out.println(ga);
		});
		
		return menuRepository.find(dimension, position, grantedAuthorities);
//		return menuRepository.findAll();
	}
	
	
	/**
	 * 
	 * @param siteId
	 * @param menuGroup
	 */
	@Transactional
	public Menu save(MenuVo menuVo) {
		logger.info("start");
		
		Long menuGroupId = menuVo.getMenuGroupId();
		Long parentMenuId = menuVo.getParentMenuId();
		
		Menu menu = beanMapper.map(menuVo, Menu.class);
		
		MenuGroup menuGroup = menuGroupRepository.findOne(menuGroupId);
		if (menuGroup == null) {
			throw new InvalidEntityException("menuGroup이 없습니다. menuGroupId: " + menuGroupId);
		}
		menu.setMenuGroup(menuGroup);

		if(parentMenuId != null) {
			Menu parent = menuRepository.findOne(parentMenuId);
			if (parent == null) {
				throw new InvalidEntityException("parent가 없습니다. parentMenuId: " + parentMenuId);
			}
			menu.setParent(parent);
		}
		
		logger.info(org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(menu));
		return menuRepository.save(menu);
	}
	
	@Transactional(readOnly=true)
	public List<Menu> getMenus(Long menuGroupId) {
		logger.info("start");
		
		return menuGroupRepository.findOne(menuGroupId).getMenus();
	}
	
	/**
	 * 메뉴 정보 수정
	 * @author 정명성
	 * create date : 2016. 4. 12.
	 * @param menuId
	 * @param menuVo
	 */
	@Transactional
	public void modify(Long menuId, MenuVo menuVo) {
		logger.info("start");
		
		Menu menu = menuRepository.findOne(menuId);
		if(menu == null) {
			throw new NotFoundException("존재하지 않는 메뉴 입니다. menuId:" + menuId);
		}
		
		beanMapper.map(menuVo, menu);
	}
}
