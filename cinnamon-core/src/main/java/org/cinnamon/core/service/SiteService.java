package org.cinnamon.core.service;

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.Property;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.enumeration.DefinedDBProperty;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.MenuGroupRepository;
import org.cinnamon.core.repository.PropertyRepository;
import org.cinnamon.core.repository.SiteRepository;
import org.cinnamon.core.vo.SiteVo;
import org.cinnamon.core.vo.search.SiteSearch;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 
 * create date: 2015. 3. 25.
 * @author 동성
 *
 */
@Service
public class SiteService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SiteRepository siteRepository;
	
	@Autowired
	MenuGroupRepository menuGroupRepository;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	Mapper beanMapper;
	
	
	/**
	 * 
	 * @return
	 */
//	@Transactional(readOnly=true)
//	public List<Site> getAll() {
//		logger.info("start");
//		
//		return siteRepository.findAll();
//	}
	
	
	@Transactional(readOnly=true)
	public Page<Site> getList(SiteSearch siteSearch, Pageable pageable) {
		logger.info("start");
		
		return siteRepository.findAll(pageable);
	}
	
	
	/**
	 * 
	 * @param siteId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Site get(String siteId) {
		logger.info("start");
		
		return siteRepository.findOne(siteId);
	}
	
	
	/**
	 * 
	 * @param siteId
	 * @return
	 */
//	@Transactional(readOnly=true)
//	public MenuGroup getDefaultMenuGroup(String siteId) {
//		logger.info("start");
//		
//		Site site = siteRepository.findOne(siteId);
//		if(site == null) {
//			throw new NotFoundException("존재하지 않는 사이트 입니다. siteId : " + siteId);
//		}
//		
//		return site.getDefaultMenuGroup();
//	}
	
	
	/**
	 * 사이트 기본 메뉴 등록
	 * @author 정명성
	 * create date : 2016. 3. 17.
	 * @param siteId
	 * @param menuGroupVo
	 */
	@Transactional
	public void setDefaultMenuGroup(String siteId, Long menuGroupId) {
		logger.info("start");
		
		Site site = siteRepository.findOne(siteId);
		if(site == null) {
			throw new NotFoundException("존재하지 않는 사이트 입니다. siteId : " + siteId);
		}
		
		MenuGroup menuGroup = menuGroupRepository.findByMenuGroupIdAndSiteSiteId(menuGroupId, siteId);
		if(menuGroup == null) {
			throw new NotFoundException("존재하지 않는 메뉴 그룹입니다. menuGroupId : " + menuGroupId);
		}
		
		site.setDefaultMenuGroup(menuGroup);
	}
	
	
	/**
	 * 지울수 있는지 확인
	 * 하위에 메뉴그룹이 만들어져 있으면 지울 수 없음
	 * @param siteId
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean isDeleteable(String siteId) {
		logger.info("start");
		
		if (menuGroupRepository.countBySiteSiteId(siteId) > 0) {
			return false;
		}
		
		return true;
	}
	
	@Transactional
	public void delete(String siteId) {
		logger.info("start");
		
		siteRepository.delete(siteId);
	}
	
	
	/**
	 * 
	 * @param siteVo
	 * @return
	 */
	@Transactional
	public Site save(SiteVo siteVo) {
		logger.info("start");
		
		Site site = beanMapper.map(siteVo, Site.class);
		
		siteRepository.save(site);
		return site;
	}
	
	
	/**
	 * 기본 사이트를 전달한다.
	 * 기본 사이트 지정이 되어 있으면 그걸 주고없으면 검색해서 처음 나온것을 전달한다. 등록된 사이트가 없으면 null을 전달한다.
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public Site getDefault() {
		logger.info("start");
		
		Property property = propertyRepository.findOne(DefinedDBProperty.defaultSiteId.name());
		if (property != null) {
			String defaultSiteId = property.getValue();
			if (!StringUtils.isEmpty(defaultSiteId)) {
				return siteRepository.findOne(defaultSiteId);
			}
		}
		
		PageRequest pageable = new PageRequest(0, 1);
		Page<Site> sites = siteRepository.findAll(pageable);
		if (sites.getContent().size() > 0) {
			return sites.getContent().get(0);
		}
		
		return null;
	}
	
	
	/**
	 * site 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 17.
	 * @param siteId
	 * @param siteVo
	 */
	@Transactional
	public void modify(String siteId, SiteVo siteVo) {
		logger.info("start");
		
		Site site = siteRepository.findOne(siteId);
		if(site == null) {
			throw new NotFoundException("site가 존재하지 않습니다. siteId : " + siteId);
		}
		
		beanMapper.map(siteVo, site);
	}
}
