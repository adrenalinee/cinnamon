package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.QUserBase;
import org.cinnamon.core.domain.QUserGroup;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.vo.search.UserGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;

/**
 * 
 * @author 동성
 * @since 2015. 2. 5.
 */
public class UserGroupRepositoryImpl extends QuerydslRepositorySupport implements UserGroupRepositoryCustom<UserBase> {
	
	@Autowired
	EntityManager em;
	
	public UserGroupRepositoryImpl() {
		super(UserGroup.class);
	}
	
	
	@Override
	public Page<UserGroup> search(UserGroupSearch userGroupSearch, Pageable pageable) {
		QUserGroup userGroup = QUserGroup.userGroup;
		
		JPQLQuery<UserGroup> query = from(userGroup);
		if (!StringUtils.isEmpty(userGroupSearch.getKeyword())) {
			String keyword = userGroupSearch.getKeyword();
			query.where(userGroup.name.like("%" + keyword + "%"));
		}
		
		if (!StringUtils.isEmpty(userGroupSearch.getUserId())) {
			QUserBase user = QUserBase.userBase;
			
			query.innerJoin(userGroup.users, user);
			query.where(user.userId.eq(userGroupSearch.getUserId()));
		}
		
		if (userGroupSearch.getUserGroupId() != null) {
			query.where(userGroup.userGroupId.eq(userGroupSearch.getUserGroupId()));
		}
		if (!StringUtils.isEmpty(userGroupSearch.getName())) {
			query.where(userGroup.name.like("%" + userGroupSearch.getName() + "%"));
		}
		if (!StringUtils.isEmpty(userGroupSearch.getAuthority())) {
			query.where(userGroup.permission.authority.eq(userGroupSearch.getAuthority()));
		}
		if (userGroupSearch.getUseStatus() != null) {
			query.where(userGroup.useStatus.eq(userGroupSearch.getUseStatus()));
		}
		
		
		List<UserGroup> domains = getQuerydsl().applyPagination(pageable, query).fetch();
		long totalCount = query.fetchCount();
		
		return new PageImpl<UserGroup>(domains, pageable, totalCount);
	}

}
