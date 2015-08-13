package org.cinnamon.core.repository;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.QMenuGroup;
import org.cinnamon.core.vo.search.MenuGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
@Repository
public class MenuGroupRepository {
	
	@Autowired
	EntityManager em;
	
	public void persist(MenuGroup menuGroup) {
		em.persist(menuGroup);
	}
	
	
	public MenuGroup findById(Long menuGroupId) {
		return em.find(MenuGroup.class, menuGroupId);
	}
	
	
	public MenuGroup getSiteMenuPosition(String siteId, String dimension) {
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		JPAQuery query = new JPAQuery(em).from(menuGroup);
		query.where(menuGroup.site.siteId.eq(siteId).and(menuGroup.dimension.eq(dimension)));
		
		return query.singleResult(menuGroup);
	}
	
	
//	public List<MenuGroup> getSites() {
//		
//		return em.createQuery(
//				"select mg.site from MenuGroup mg " +
//				"group by mg.site", MenuGroup.class)
//				.getResultList();
//		
//		
//	}
	
	public List<MenuGroup> getSiteScene(String siteId) {
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		
		JPAQuery query = new JPAQuery(em).from(menuGroup);
		query
			.where(menuGroup.site.siteId.eq(siteId))
//			.limit(100)
//			.orderBy(menuGroup.menuGroupId.desc())
			.groupBy(menuGroup.dimension);
		
		List<MenuGroup> menuGroups = new LinkedList<MenuGroup>();
		query.list(menuGroup.dimension).forEach(dimension -> {
			System.out.println(dimension);
			MenuGroup mg = new MenuGroup();
			mg.setDimension(dimension);
			
			menuGroups.add(mg);
		});
		
		return menuGroups;
	}
	
	public List<MenuGroup> find(String siteId) {
		return em.createQuery("select mg from MenuGroup mg join mg.site s where s.siteId = :siteId", MenuGroup.class)
				.setParameter("siteId", siteId)
				.getResultList();
	}
	

	public Page<MenuGroup> find(MenuGroupSearch menuGroupSearch, Pageable pageable) {
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (menuGroupSearch.getMenuGroupId() != null) {
			builder.and(menuGroup.menuGroupId.eq(menuGroupSearch.getMenuGroupId()));
		}
		if (!StringUtils.isEmpty(menuGroupSearch.getDimension())) {
			builder.and(menuGroup.dimension.like("%" + menuGroupSearch.getDimension() + "%"));
		}
//		if (!StringUtils.isEmpty(menuGroupSearch.getPosition())) {
//			builder.and(menuGroup.position.eq(MenuGroupPosition.valueOf(menuGroupSearch.getPosition())));
//		}
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(menuGroup);
		query
			.where(builder)
			.offset(offset)
			.limit(limit)
			.orderBy(menuGroup.menuGroupId.desc());
		
		
		List<MenuGroup> domains = query.list(menuGroup);
		long totalCount = query.count();
		
		Page<MenuGroup> page = new PageImpl<MenuGroup>(domains, pageable, totalCount);
		
		return page;
	}
}
