package org.cinnamon.core.repository;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.MenuGroup;
import org.cinnamon.core.domain.QMenu;
import org.cinnamon.core.domain.QMenuGroup;
import org.cinnamon.core.domain.QSite;
import org.cinnamon.core.vo.search.MenuGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.util.StringUtils;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
public class MenuGroupRepositoryImpl extends QueryDslRepositorySupport implements MenuGroupRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	
	public MenuGroupRepositoryImpl() {
		super(MenuGroup.class);
	}
	
	@Override
	public MenuGroup getSiteMenuPosition(String siteId, String dimension) {
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		JPAQuery query = new JPAQuery(em).from(menuGroup);
		query.where(menuGroup.site.siteId.eq(siteId).and(menuGroup.dimension.eq(dimension)));
		
		return query.singleResult(menuGroup);
	}
	
	
	@Override
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
	
	@Override
	public List<MenuGroup> find(String siteId) {
		return em.createQuery("select mg from MenuGroup mg join mg.site s where s.siteId = :siteId", MenuGroup.class)
				.setParameter("siteId", siteId)
				.getResultList();
	}

	@Override
	public Page<MenuGroup> find(MenuGroupSearch menuGroupSearch, Pageable pageable) {
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		
		JPQLQuery query = from(menuGroup);
		if (menuGroupSearch.getMenuGroupId() != null) {
			query.where(menuGroup.menuGroupId.eq(menuGroupSearch.getMenuGroupId()));
		}
		if (!StringUtils.isEmpty(menuGroupSearch.getSiteId())) {
			QSite site = QSite.site;
			
			query
				.innerJoin(menuGroup.site, site)
				.where(site.siteId.eq(menuGroupSearch.getSiteId()));
		}
		if (!StringUtils.isEmpty(menuGroupSearch.getDimension())) {
			query.where(menuGroup.dimension.eq(menuGroupSearch.getDimension()));
		}
		if (menuGroupSearch.getHasdMenuId() != null) {
			QMenu menu = QMenu.menu;
			
			query
				.innerJoin(menuGroup.menus, menu)
				.where(menu.menuId.eq(menuGroupSearch.getHasdMenuId()));
		}
		
		
		List<MenuGroup> domains = getQuerydsl().applyPagination(pageable, query).list(menuGroup);
		long totalCount = query.count();
		
		Page<MenuGroup> page = new PageImpl<MenuGroup>(domains, pageable, totalCount);
		
		return page;
	}
}
