package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.QUserBase;
import org.cinnamon.core.domain.QUserGroup;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.vo.search.UserBaseSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;

/**
 * 
 * @author 신동성
 * @since 2016. 6. 7.
 */
public class UserBaseRepositoryImpl extends QueryDslRepositorySupport implements UserBaseRepositoryCustom<UserBase> {
	
	@Autowired
	EntityManager em;
	
	public UserBaseRepositoryImpl() {
		super(UserBase.class);
	}
	
	@Override
	public Page<UserBase> find(UserBaseSearch userBaseSearch, Pageable pageable) {
		QUserBase user = QUserBase.userBase; // new QUserBase("u");
		
		JPQLQuery<UserBase> query = from(user);
		if (!StringUtils.isEmpty(userBaseSearch.getKeyword())) {
			String keyword = userBaseSearch.getKeyword();
			query.where(
				user.name.like("%" + keyword + "%")
				.or(user.userId.like("%" + keyword + "%"))
				.or(user.email.like("%" + keyword + "%")));
		}
		
		if (!StringUtils.isEmpty(userBaseSearch.getUserId())) {
			query.where(user.userId.like("%" + userBaseSearch.getUserId() + "%"));
		}
		if (!StringUtils.isEmpty(userBaseSearch.getName())) {
			query.where(user.name.like("%" + userBaseSearch.getName() + "%"));
		}
		if (!StringUtils.isEmpty(userBaseSearch.getEmail())) {
			query.where(user.email.like("%" + userBaseSearch.getEmail() + "%"));
		}
		if (!StringUtils.isEmpty(userBaseSearch.getNation())) {
			query.where(user.nation.eq(userBaseSearch.getNation()));
		}
		
		if (userBaseSearch.getUserGroupId() != null) {
			QUserGroup userGroup = QUserGroup.userGroup;
			query.innerJoin(user.userGroups, userGroup)
				.where(userGroup.userGroupId.eq(userBaseSearch.getUserGroupId()));
		}
		
		
		List<UserBase> domains = getQuerydsl().applyPagination(pageable, query).fetch();
		long totalCount = query.fetchCount();
		
		return new PageImpl<UserBase>(domains, pageable, totalCount);
	}

}
