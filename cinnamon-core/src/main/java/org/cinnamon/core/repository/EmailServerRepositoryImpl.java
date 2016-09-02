package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.EmailServer;
import org.cinnamon.core.domain.QEmailServer;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.vo.search.EmailServerSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * 
 * create date: 2015. 4. 20.
 * @author 동성
 *
 */
public class EmailServerRepositoryImpl extends QueryDslRepositorySupport implements EmailServerRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	public EmailServerRepositoryImpl() {
		super(EmailServer.class);
	}
	
	@Override
	public EmailServer getDefault() {
		QEmailServer emailServer = QEmailServer.emailServer;
		
		return from(emailServer)
			.where(emailServer.defaultServer.eq(true))
			.fetchOne();
	}
	
	
	/**
	 * 
	 * @param emailServerSearch
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<EmailServer> search(EmailServerSearch emailServerSearch, Pageable pageable) {
		QEmailServer emailServer = QEmailServer.emailServer;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (emailServerSearch.getEmailServerId() != null) {
			builder.and(emailServer.emailServerId.eq(emailServerSearch.getEmailServerId()));
		}
		if (!StringUtils.isEmpty(emailServerSearch.getName())) {
			builder.and(emailServer.name.like("%" + emailServerSearch.getName() + "%"));
		}
		if (!StringUtils.isEmpty(emailServerSearch.getAddress())) {
			builder.and(emailServer.address.like("%" + emailServerSearch.getAddress() + "%"));
		}
		// 2016.03.09 추가
		builder.and(emailServer.useStatus.eq(UseStatus.enable));
		
		JPQLQuery<EmailServer> query = from(emailServer).where(builder);
		
		List<EmailServer> domains = getQuerydsl().applyPagination(pageable, query).fetch();
		long totalCount = query.fetchCount();
		
		Page<EmailServer> page = new PageImpl<EmailServer>(domains, pageable, totalCount);
		
		return page;
	}
}
