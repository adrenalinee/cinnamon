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
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
public class SiteRepositoryImpl extends QuerydslRepositorySupport implements SiteRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	public SiteRepositoryImpl() {
		super(Site.class);
	}
	
	
	@Override
	public List<Site> findAll() {
		QSite site = QSite.site;
		
		return from(site)
				.limit(100)
				.orderBy(site.createdAt.asc())
				.fetch();
	}
	
	@Override
	public Page<Site> search(SiteSearch siteSearch, Pageable pageable) {
		QSite site = QSite.site;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (siteSearch.getSiteId() != null) {
			builder.and(site.siteId.eq(siteSearch.getSiteId()));
		}
		if (!StringUtils.isEmpty(siteSearch.getName())) {
			builder.and(site.name.like("%" + siteSearch.getName() + "%"));
		}
		
		JPQLQuery<Site> query = from(site)
			.where(builder)
			.orderBy(site.createdAt.desc());
		
		
		List<Site> domains = getQuerydsl().applyPagination(pageable, query).fetch();
		long totalCount = query.fetchCount();
		
		return new PageImpl<Site>(domains, pageable, totalCount);
	}
}
