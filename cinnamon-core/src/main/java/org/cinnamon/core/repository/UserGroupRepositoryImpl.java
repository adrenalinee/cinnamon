package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.QUserGroup;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.vo.search.UserGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * @author 동성
 * @since 2015. 2. 5.
 */
public class UserGroupRepositoryImpl implements UserGroupRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	
	@Override
	public Page<UserGroup> search(UserGroupSearch userGroupSearch, Pageable pageable) {
		QUserGroup userGroup = QUserGroup.userGroup;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (!StringUtils.isEmpty(userGroupSearch.getKeyword())) {
			String keyword = userGroupSearch.getKeyword();
//			Long longValue = null;
//			BooleanBuilder builder2 = new BooleanBuilder();
//			try {
//				longValue = Long.parseLong(keyword);
//			} catch (NumberFormatException e) {
//				
//			}
//			if (longValue != null) {
//				builder2.or(userGroup.userGroupId.eq(longValue));
//			}
//			
//			builder2.or(userGroup.name.like("%" + keyword + "%"))
//					.or(userGroup.authority.authority.eq(keyword));
//			
//			builder.or(builder2);
			
			builder.or(userGroup.name.like("%" + keyword + "%"))
					.or(userGroup.authority.authority.eq(keyword));
		}
		
		if (userGroupSearch.getUserGroupId() != null) {
			builder.and(userGroup.userGroupId.eq(userGroupSearch.getUserGroupId()));
		}
		if (!StringUtils.isEmpty(userGroupSearch.getName())) {
			builder.and(userGroup.name.like("%" + userGroupSearch.getName() + "%"));
		}
		if (!StringUtils.isEmpty(userGroupSearch.getAuthority())) {
			builder.and(userGroup.authority.authority.eq(userGroupSearch.getAuthority()));
		}
		if (userGroupSearch.getUseStatus() != null) {
			builder.and(userGroup.useStatus.eq(userGroupSearch.getUseStatus()));
		}
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(userGroup);
		query
			.where(builder)
			.offset(offset)
			.limit(limit)
			.orderBy(userGroup.userGroupId.desc(), userGroup.name.desc());
		
		
		List<UserGroup> domains = query.list(userGroup);
		long totalCount = query.count();
		
		Page<UserGroup> page = new PageImpl<UserGroup>(domains, pageable, totalCount);
		
		return page;
	}

}
