package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.QUserBase;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.vo.search.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;

/**
 * 
 * @author 동성
 *
 */
//@Repository("configuration.userBaseRepository")
public class UserBase_RepositoryImpl extends QueryDslRepositorySupport implements UserBase_RepositoryCustom<UserBase> {
	
	@Autowired
	EntityManager em;
	
	
	public UserBase_RepositoryImpl() {
		super(UserBase.class);
	}
	
	
//	@Override
//	public long allCount() {
//		QUser user = QUser.user;
//		return from(user).count();
//		
////		JPAQuery query = new JPAQuery(em).from(user);
////		
////		return query.count();
//	}
	
	
//	public User findById(String userId) {
//		return em.find(User.class, userId);
//	}
	
	
	@Override
	public Page<UserBase> search1(UserSearch userSearch, Pageable pageable) {
		QUserBase user = QUserBase.userBase;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (userSearch.getUserId() != null) {
			builder.and(user.userId.eq(userSearch.getUserId()));
		}
		
		if (!StringUtils.isEmpty(userSearch.getName())) {
			builder.and(user.name.like("%" + userSearch.getName() + "%"));
		}
		
		if (userSearch.getUserGroupId() != null) {
//			builder.and(user.userGroup.userGroupId.eq(userSearch.getUserGroupId()));
			builder.and(user.userGroups.any().userGroupId.eq(userSearch.getUserGroupId()));
		}
		
		
//		long offset = pageable.getOffset();
//		long limit = pageable.getPageSize();
		
//		JPAQuery query = new JPAQuery(em).from(user);
//		query
//			.where(builder)
//			.offset(offset)
//			.limit(limit)
//			.orderBy(user.createdAt.desc());
//		
//		List<User> domains = query.list(user);
		
		JPQLQuery query = from(user).where(builder);
		List<UserBase> domains = getQuerydsl()
				.applyPagination(pageable, query)
				.list(user);
		long totalCount = query.count();
		
		Page<UserBase> page = new PageImpl<UserBase>(domains, pageable, totalCount);
		
		return page;
	}
	
	
	
	
//	public Page<Administrator> find(AdministratorSearch administratorSearch, Pageable pageable) {
//		QAdministrator administrator = QAdministrator.administrator;
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		if (administratorSearch.getAdminId() != null) {
//			builder.and(administrator.adminId.eq(administratorSearch.getAdminId()));
//		}
//		
//		if (!StringUtils.isEmpty(administratorSearch.getName())) {
//			builder.and(administrator.name.like("%" + administratorSearch.getName() + "%"));
//		}
//		
//		long offset = pageable.getOffset();
//		long limit = pageable.getPageSize();
//		
//		JPAQuery query = new JPAQuery(em).from(administrator);
//		query
//			.where(builder)
//			.offset(offset)
//			.limit(limit)
//			.orderBy(administrator.createdAt.desc());
//		
//		
//		List<Administrator> domains = query.list(administrator);
//		long totalCount = query.count();
//		
//		Page<Administrator> page = new PageImpl<Administrator>(domains, pageable, totalCount);
//		
//		return page;
//	}
	
	
	
//	public void persist(User user) {
//		em.persist(user);
//	}


//	@Override
//	public User findByEmail(String email) {
//		QUser user = QUser.user;
//		
//		JPAQuery query = new JPAQuery(em).from(user);
//		return query.where(user.email.eq(email)).singleResult(user);
//	}
	
	
}
