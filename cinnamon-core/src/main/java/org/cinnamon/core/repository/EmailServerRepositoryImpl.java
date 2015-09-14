package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.EmailServer;
import org.cinnamon.core.domain.QEmailServer;
import org.cinnamon.core.vo.search.EmailServerSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * create date: 2015. 4. 20.
 * @author 동성
 *
 */
public class EmailServerRepositoryImpl implements EmailServerRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	
	@Override
	public EmailServer getDefault() {
		QEmailServer emailServer = QEmailServer.emailServer;
		
		JPAQuery query = new JPAQuery(em).from(emailServer);
		return query.where(emailServer.defaultServer.eq(true)).singleResult(emailServer);
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
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(emailServer);
		query
			.where(builder)
			.offset(offset)
			.limit(limit)
			.orderBy(emailServer.createdAt.desc());
		
		
		List<EmailServer> domains = query.list(emailServer);
		long totalCount = query.count();
		
		Page<EmailServer> page = new PageImpl<EmailServer>(domains, pageable, totalCount);
		
		return page;
	}
}
