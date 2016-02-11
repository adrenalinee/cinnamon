package org.cinnamon.core.repository.predicate;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.QUserBase;
import org.cinnamon.core.vo.search.UserBaseSearch;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

/**
 * 
 * @author dsshin
 *
 */
public class UserBasePredicate {
	
	public static Predicate search(UserBaseSearch userSearch) {
		QUserBase user = QUserBase.userBase;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (!StringUtils.isEmpty(userSearch.getKeyword())) {
			String keyword = userSearch.getKeyword();
			builder.and(
				user.name.like("%" + keyword + "%")
				.or(user.userId.like("%" + keyword + "%"))
				.or(user.email.like("%" + keyword + "%")));
		}
		
		
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
		
		return builder;
	}
}
