package org.cinnamon.core.service;

import java.util.List;

import org.cinnamon.core.domain.Property;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.enumeration.DefinedDBProperty;
import org.cinnamon.core.repository.PropertyRepository;
import org.cinnamon.core.repository.SiteRepository;
import org.cinnamon.core.vo.SiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	PropertyRepository propertyRepository;
	
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Site> getAll() {
		logger.info("start");
		
		return siteRepository.findAll();
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
	 * @param siteVo
	 * @return
	 */
	@Transactional
	public Site save(SiteVo siteVo) {
		logger.info("start");
		
		Site site = new Site();
		BeanUtils.copyProperties(siteVo, site);
		
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
	
}
