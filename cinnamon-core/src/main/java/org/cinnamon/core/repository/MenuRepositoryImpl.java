package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.QMenu;
import org.cinnamon.core.domain.QMenuAuthority;
import org.cinnamon.core.domain.QUserAuthority;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.vo.search.MenuSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * @author 동성
 *
 */
public class MenuRepositoryImpl extends QueryDslRepositorySupport implements MenuRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	public MenuRepositoryImpl() {
		super(Menu.class);
	}
	
	
	
	@Override
	public Page<Menu> search(MenuSearch menuSearch, Pageable pageable) {
		QMenu menu = QMenu.menu;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (menuSearch.getMenuId() != null) {
			builder.and(menu.menuId.eq(menuSearch.getMenuId()));
		}
		if (!StringUtils.isEmpty(menuSearch.getName())) {
			builder.and(menu.name.like("%" + menuSearch.getName() + "%"));
		}
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(menu);
		query
			.where(builder)
			.offset(offset)
			.limit(limit)
			.orderBy(menu.orders.asc());
		
		
		List<Menu> domains = query.list(menu);
		long totalCount = query.count();
		
		Page<Menu> page = new PageImpl<Menu>(domains, pageable, totalCount);
		
		return page;
	}
	
	
	
//	public List<Menu> findByMenuGroupId(Long menuGroupId) {
//		
//		return null;
//	}
	
	
	/**
	 * 
	 * @param site
	 * @param dimension
	 * @param position
	 * @param authority
	 * @return
	 */
	@Override
	public List<Menu> getSitePermisionMenus(String site, String dimension, MenuPosition position, String authority) {
		return em.createQuery(
				"select m " +
				"from Menu m " +
//				"join m.menuGroups mg " +
				"join m.menuGroup mg " +
				"join mg.site s " +
				"join m.roleMenus pm " + 
				"join pm.role p " +
				"where s.siteId = :site and " +
					"mg.dimension = :dimension and " +
					"p.authority = :authority and " +
					"m.position = :position and " +
					"m.useStatus = :useStatus " +
				"order by m.orders asc", Menu.class)
					.setParameter("site", site)
					.setParameter("dimension", dimension)
					.setParameter("position", position)
					.setParameter("authority", authority)
					.setParameter("useStatus", UseStatus.enable)
					.setMaxResults(100)
					.getResultList();
	}
	
	
	@Override
	public List<Menu> getSitePermisionMenus(String site, String dimension, MenuPosition position, List<String> authorities) {
		return em.createQuery(
				"select distinct m " +
				"from Menu m " +
//				"join m.menuGroups mg " +
				"join m.menuGroup mg " +
				"join mg.site s " +
				"join m.roleMenus pm " + 
				"join pm.role p " +
				"where s.siteId = :site and " +
					"pm.permitRoot = true and " +
					"mg.dimension = :dimension and " +
//					"p.authority = :authority and " +
					"p.authority in :authorities and " +
					"m.position = :position and " +
					"m.useStatus = :useStatus and " +
					"m.parent = null " +
				"order by m.orders asc", Menu.class)
					.setParameter("site", site)
					.setParameter("dimension", dimension)
					.setParameter("position", position)
//					.setParameter("authority", authority)
					.setParameter("authorities", authorities)
					.setParameter("useStatus", UseStatus.enable)
					.setMaxResults(100)
					.getResultList();
	}
	
	
	@Override
	public List<Menu> findByAuthority(String authority) {
		QMenu menu = QMenu.menu;
		QMenuAuthority roleMenu = QMenuAuthority.menuAuthority;
		QUserAuthority role = QUserAuthority.userAuthority;
		
		return new JPAQuery(em).from(menu)
				.join(menu.grantedRoles, roleMenu)
				.join(roleMenu.authority, role)
				.where(role.authority.eq(authority))
				.orderBy(menu.orders.asc())
				.limit(100)
				.listResults(menu).getResults();
		
		
		
//		return em.createQuery(
//				"select m " +
//				"from Menu m join m.roleMenus pm join pm.role p " +
//				"where p.authority = :authority", Menu.class)
//					.setParameter("authority", authority)
//					.setMaxResults(100)
//					.getResultList();
	}

}
