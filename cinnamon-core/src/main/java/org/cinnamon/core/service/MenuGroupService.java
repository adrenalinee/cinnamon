package org.cinnamon.core.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.exception.InvalidEntityException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.SiteRepository;
import org.cinnamon.core.vo.MenuGroupVo;
import org.cinnamon.core.vo.search.MenuGroupSearch;
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
public class MenuGroupService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SiteRepository siteRepository;
	
	@Autowired
	MenuGroupRepository menuGroupRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	Mapper beanMapper;
	
	
	@Transactional(readOnly=true)
	public Page<MenuGroup> getList(MenuGroupSearch menuGroupSearch, Pageable pageable) {
		logger.info("start");
		
		System.out.println(ToStringBuilder.reflectionToString(menuGroupSearch));
		
		return menuGroupRepository.find(menuGroupSearch, pageable);
	}
	
	
	@Transactional(readOnly=true)
	public List<MenuGroup> getSiteMenuGroups(String siteId) {
		logger.info("start");
		
		//return menuGroupRepository.getSiteScene(siteId);
		return menuGroupRepository.find(siteId);
	}
	
	@Transactional
	public MenuGroup save(MenuGroupVo menuGroupVo) {
		logger.info("start");
		
		Site site = siteRepository.findOne(menuGroupVo.getSiteId());
		if (site == null) {
			throw new InvalidEntityException("site가 없습니다. siteId: " + menuGroupVo.getSiteId());
		}
		
		MenuGroup menuGroup = beanMapper.map(menuGroupVo, MenuGroup.class);
		menuGroup.setSite(site);
		
		return menuGroupRepository.save(menuGroup);
	}
	
	
	/**
	 * 
	 * @param siteId
	 * @param menuGroup
	 */
	@Transactional
	public void save(String siteId, MenuGroup menuGroup) {
		logger.info("start");
		
		Site site = siteRepository.findOne(siteId);
		if (site == null) {
			throw new InvalidEntityException("site가 없습니다. siteId: " + siteId);
		}
		menuGroup.setSite(site);
//		menuGroup.setPosition(MenuGroupPosition.sidebar);
		
		menuGroupRepository.save(menuGroup);
	}

	
	/**
	 * 
	 * @param menuGroupId
	 * @return
	 */
	@Transactional(readOnly=true)
	public MenuGroup get(Long menuGroupId) {
		logger.info("start");
		return menuGroupRepository.findOne(menuGroupId);
	}
	
	
	@Transactional(readOnly=true)
	public MenuGroup getByDimension(String dimension) {
		logger.info("start");
		
		return menuGroupRepository.findByDimension(dimension);
	}
	
	
	/**
	 * 
	 * @author 정명성
	 * create date : 2016. 3. 17.
	 * @param siteId
	 * @param menuGroupId
	 */
	@Transactional
	public void deleteDefaultMenuGroup(String siteId, Long menuGroupId) {
		logger.info("start");
		
		Site site = siteRepository.findBySiteIdAndDefaultMenuGroupMenuGroupId(siteId, menuGroupId);
		if(site == null) {
			throw new NotFoundException("존재하지 않는 사이트 입니다. siteId : " + siteId);
		}
		site = null;
	}
	
	/**
	 * 메뉴 그룹 별 사이트 목록 가져오기
	 * @author 정명성
	 * create date : 2016. 3. 21.
	 * @param menuGroupId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Site getSitesOfMenuGroup (Long menuGroupId) {
		logger.info("start");
		return siteRepository.findByMenuGroupMenuGroupId(menuGroupId);
	}
	
	/**
	 * 메뉴 그룹의 사이트를 지정한다.
	 * @author 정명성
	 * create date : 2016. 3. 21.
	 * @param siteId
	 * @param menuGroupId
	 */
	@Transactional
	public void putSiteOfMenuGroup(Long menuGroupId, String siteId) {
		logger.info("start");
		
		Site site = siteRepository.findOne(siteId);
		if(site == null) {
			throw new NotFoundException("존재하지 않는 사이트 입니다. siteId : " + siteId);
		}
		MenuGroup menuGroup = menuGroupRepository.findOne(menuGroupId);
		if(menuGroup == null) {
			throw new NotFoundException("존재하지 않는 메뉴 그룹 입니다. menuGroupId : " + menuGroupId);
		}
		menuGroup.setSite(site);
	}
	
	
	/**
	 * 지울수 있는지 확인
	 * 하위에 메뉴가 만들어져 있으면 지울 수 없음
	 * @param menuGroupId
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean isDeleteable(Long menuGroupId) {
		logger.info("start");
		
		if (menuRepository.countByMenuGroupMenuGroupId(menuGroupId) > 0) {
			return false;
		}
		
		return true;
	}
	
	@Transactional
	public void delete(Long menuGroupId) {
		logger.info("start");
		
		menuGroupRepository.delete(menuGroupId);
	}
}
