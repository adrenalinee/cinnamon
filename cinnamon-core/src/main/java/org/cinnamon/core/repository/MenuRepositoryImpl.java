package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.QMenu;
import org.cinnamon.core.domain.QMenuGroup;
import org.cinnamon.core.domain.QPermission;
import org.cinnamon.core.domain.QPermissionMenu;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.vo.search.MenuSearch;
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
 * @author shindongseong
 *
 */
public class MenuRepositoryImpl extends QueryDslRepositorySupport implements MenuRepositoryCustom {

	@Autowired
	EntityManager em;
	
	public MenuRepositoryImpl() {
		super(Menu.class);
	}
	
	
	@Override
	public List<Menu> find(String dimension, MenuPosition position, List<String> grantedAuthorities) {
		QMenu menu = QMenu.menu;
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		QPermissionMenu menuAuthority = QPermissionMenu.permissionMenu;
		QPermission userAuthority = QPermission.permission;
		
		return from(menu).distinct()
		.innerJoin(menu.menuGroup, menuGroup)
		.innerJoin(menu.grantedAuthorities, menuAuthority)
		.innerJoin(menuAuthority.permission, userAuthority)
		.where(
			menu.position.eq(position)
			.and(menu.parent.isNull())
			.and(menu.useStatus.eq(UseStatus.enable))
			.and(menuGroup.dimension.eq(dimension))
			.and(userAuthority.authority.in(grantedAuthorities)))
		.orderBy(menu.orders.asc())
		.limit(100)
		.list(menu);
		
//		return from(menu).distinct()
//				.where(
//						menu.position.eq(position)
//						.and(menu.parent.isNull())
//						.and(menu.useStatus.eq(UseStatus.enable))
//						.and(menu.menuGroup.dimension.eq(dimension))
//						.and(menu.grantedAuthorities.any().authority.authority.in(grantedAuthorities))
//				).orderBy(menu.orders.asc())
//				.limit(100)
//				.list(menu);
	}
	
	
	@Override
	public List<Menu> findByAuthority(String authority) {
		QMenu menu = QMenu.menu;
		QPermissionMenu roleMenu = QPermissionMenu.permissionMenu;
		QPermission role = QPermission.permission;
		
		return new JPAQuery(em).from(menu)
				.join(menu.grantedAuthorities, roleMenu)
				.join(roleMenu.permission, role)
				.where(role.authority.eq(authority))
				.orderBy(menu.orders.asc())
				.limit(100)
				.listResults(menu).getResults();
	}


	@Override
	public Page<Menu> find(MenuSearch menuSearch, Pageable pageable) {
		QMenu menu = QMenu.menu;
		
		JPQLQuery query = from(menu);
		if (menuSearch.getMenuId() != null) {
			query.where(menu.menuId.eq(menuSearch.getMenuId()));
		}
		if (menuSearch.getParentMenuId() != null) {
			QMenu parent = new QMenu("parent");
			query.innerJoin(menu.parent, parent)
				.where(parent.menuId.eq(menuSearch.getParentMenuId()));
		}
		if (menuSearch.getMenuGroupId() != null) {
			QMenuGroup menuGroup = QMenuGroup.menuGroup;
			query.innerJoin(menu.menuGroup, menuGroup)
				.where(menuGroup.menuGroupId.eq(menuSearch.getMenuGroupId()));
		}
		if (!StringUtils.isEmpty(menuSearch.getName())) {
			query.where(menu.name.eq(menuSearch.getName()));
		}
		if (!StringUtils.isEmpty(menuSearch.getUri())) {
			query.where(menu.uri.eq(menuSearch.getUri()));
		}
		if (menuSearch.getPosition() != null) {
			query.where(menu.position.eq(menuSearch.getPosition()));
		}
		
		
		List<Menu> domains = getQuerydsl().applyPagination(pageable, query).list(menu);
		long totalCount = query.count();
		
		Page<Menu> page = new PageImpl<Menu>(domains, pageable, totalCount);
		
		return page;
	}
}
