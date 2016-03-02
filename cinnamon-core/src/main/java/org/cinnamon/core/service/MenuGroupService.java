package org.cinnamon.core.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.exception.InvalidEntityException;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.SiteRepository;
import org.cinnamon.core.vo.search.MenuGroupSearch;
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
		return menuGroupRepository.findOne(menuGroupId);
	}
	
	
	@Transactional(readOnly=true)
	public MenuGroup getByDimension(String dimension) {
		return menuGroupRepository.findByDimension(dimension);
	}
}
