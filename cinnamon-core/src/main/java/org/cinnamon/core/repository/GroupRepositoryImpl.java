package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.Group;
import org.cinnamon.core.domain.QGroup;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.vo.search.GroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPQLQuery;

/**
 * 
 * @author 동성
 * @since 2014. 11. 19.
 */
public class GroupRepositoryImpl extends QueryDslRepositorySupport implements GroupRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	
	public GroupRepositoryImpl() {
		super(Group.class);
	}
	
	
	/**
	 * 
	 * @param parentGroupId
	 * @return
	 */
	@Override
	public List<Group> childGroups(String parentGroupId, UseStatus useStatus) {
		QGroup group = QGroup.group;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(group.parent.groupId.eq(parentGroupId));
		
		if (useStatus != null) {
			builder.and(group.useStatus.eq(useStatus));
		}
		
		return from(group).where(builder).fetch();
	}
	
	
	/**
	 * 
	 * @param groupSearch
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<Group> search(GroupSearch groupSearch, Pageable pageable) {
		QGroup group = QGroup.group;
		JPQLQuery<Group> query = from(group);
		
		if (!StringUtils.isEmpty(groupSearch.getKeyword())) {
			query.where(group.groupId.like("%" + groupSearch.getKeyword() + "%")
					.or(group.name.like("%" + groupSearch.getKeyword() + "%")));
		}
		
		if (groupSearch.getGroupId() != null) {
			query.where(group.groupId.eq(groupSearch.getGroupId()));
		}
		
		if (!StringUtils.isEmpty(groupSearch.getName())) {
			query.where(group.name.like("%" + groupSearch.getName() + "%"));
		}
		
		if (!StringUtils.isEmpty(groupSearch.getParent())) {
			query.where(group.parent.name.eq(groupSearch.getParent()));
		}
		
		//group 이라는 이름이 문제가 있어서 getQuerydsl().applyPagination()..이 정상 적용 안된다..
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		query
			.offset(offset)
			.limit(limit);
		
		if (pageable.getSort() != null) {
			pageable.getSort().forEach(sort -> {
				if(sort.getProperty().equals("groupId")) {
					setDirection(query, group.groupId, sort.getDirection());
				}
				if(sort.getProperty().equals("name")) {
					setDirection(query, group.name, sort.getDirection());
				}
				if(sort.getProperty().equals("parentGroupId")) {
					setDirection(query, group.parent.groupId, sort.getDirection());
				}
			});
		}
		
		List<Group> domains = query.fetch();
		long totalCount = query.fetchCount();
		
		Page<Group> page = new PageImpl<Group>(domains, pageable, totalCount);
		
		return page;
	}
	
	/**
	 * 코드 정렬 값 셋팅
	 * @author 정명성
	 * create date : 2016. 3. 10.
	 * @param query
	 * @param property
	 * @param direction
	 */
	public void setDirection(JPQLQuery<Group> query, StringPath property, Direction direction) {
		if (direction.equals(Direction.ASC)) {
			query.orderBy(property.asc());
		} else {
			query.orderBy(property.desc());
		}
	}
}
