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
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * @author 동성
 * @since 2014. 11. 19.
 */
@Repository
public class GroupRepository extends QueryDslRepositorySupport {
	
	@Autowired
	EntityManager em;
	
	
	public GroupRepository() {
		super(Group.class);
	}
	
	
	/**
	 * 
	 * @param groupId
	 * @return
	 */
	public Group findById(String groupId) {
		return em.find(Group.class, groupId);
	}
	
	/**
	 * 
	 * @param parentGroupId
	 * @return
	 */
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
		
		
//		TypedQuery<Group> query = em.createQuery("select g from Group as g where g.parent.groupId = :parent order by orders asc", Group.class);
//		query.setParameter("parent", parentGroupId);
//		
//		return query.getResultList();
	}
	
	
	/**
	 * 
	 * @param groupSearch
	 * @param pageable
	 * @return
	 */
	public Page<Group> find(GroupSearch groupSearch, Pageable pageable) {
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
			.limit(limit)
			.orderBy(group.parent.groupId.asc(), group.orders.asc());
		
		
		List<Group> domains = query.list(group);
		long totalCount = query.count();
		
		Page<Group> page = new PageImpl<Group>(domains, pageable, totalCount);
		
		return page;
	}

}