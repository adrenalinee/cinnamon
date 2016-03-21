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

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.StringPath;

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
		
		
		JPAQuery query = new JPAQuery(em).from(group);
		query
			.where(builder)
			.orderBy(group.orders.asc());
		
		
		return query.list(group);
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
		
		BooleanBuilder builder = new BooleanBuilder();
		if (groupSearch.getGroupId() != null) {
			builder.and(group.groupId.eq(groupSearch.getGroupId()));
		}
		
		if (!StringUtils.isEmpty(groupSearch.getName())) {
			builder.and(group.name.like("%" + groupSearch.getName() + "%"));
		}
		
		if (!StringUtils.isEmpty(groupSearch.getParent())) {
			builder.and(group.parent.groupId.eq(groupSearch.getParent()));
		}
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(group);
		query
			.where(builder)
			.offset(offset)
			.limit(limit);
			//.orderBy(group.parent.groupId.asc(), group.orders.asc());
		
		if(pageable.getSort() != null) {
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
		
		List<Group> domains = query.list(group);
		long totalCount = query.count();
		
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
	public void setDirection(JPAQuery query, StringPath property, Direction direction) {
		if(direction.equals(Direction.ASC)) {
			query.orderBy(property.asc());
		} else {
			query.orderBy(property.desc());
		}
	}
}
