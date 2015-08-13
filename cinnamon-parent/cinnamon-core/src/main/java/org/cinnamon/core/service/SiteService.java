package org.cinnamon.core.service;

import java.util.List;

import org.cinnamon.core.domain.Site;
import org.cinnamon.core.repository.SiteRepository;
import org.cinnamon.core.vo.SiteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		return siteRepository.findById(siteId);
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
		
		siteRepository.persist(site);
		return site;
	}
	
}
