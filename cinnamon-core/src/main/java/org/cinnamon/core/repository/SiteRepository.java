package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.QSite;
import org.cinnamon.core.domain.Site;
import org.cinnamon.core.vo.search.SiteSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
@Repository
public class SiteRepository {
	
	@Autowired
	EntityManager em;
	
	
	public List<Site> findAll() {
		QSite site = QSite.site;
		
		JPAQuery query = new JPAQuery(em).from(site);
		query
			.limit(100)
			.orderBy(site.createdAt.asc());
		
		return query.list(site);
	}
	
	public Page<Site> find(SiteSearch siteSearch, Pageable pageable) {
		QSite site = QSite.site;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (siteSearch.getSiteId() != null) {
			builder.and(site.siteId.eq(siteSearch.getSiteId()));
		}
		if (!StringUtils.isEmpty(siteSearch.getName())) {
			builder.and(site.name.like("%" + siteSearch.getName() + "%"));
		}
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(site);
		query
			.where(builder)
			.offset(offset)
			.limit(limit)
			.orderBy(site.createdAt.desc());
		
		
		List<Site> domains = query.list(site);
		long totalCount = query.count();
		
		Page<Site> page = new PageImpl<Site>(domains, pageable, totalCount);
		
		return page;
	}


	public Site findById(String siteId) {
		return em.find(Site.class, siteId);
	}


	public void persist(Site site) {
		em.persist(site);
	}
	
	
}
