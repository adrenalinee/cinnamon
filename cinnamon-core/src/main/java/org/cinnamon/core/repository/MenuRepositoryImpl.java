package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.QMenu;
import org.cinnamon.core.domain.QMenuAuthority;
import org.cinnamon.core.domain.QMenuGroup;
import org.cinnamon.core.domain.QUserAuthority;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

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
	
	public List<Menu> find(String dimension, MenuPosition position, List<String> grantedAuthorities) {
		QMenu menu = QMenu.menu;
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		QMenuAuthority menuAuthority = QMenuAuthority.menuAuthority;
		QUserAuthority userAuthority = QUserAuthority.userAuthority;
		
		return from(menu)
		.innerJoin(menu.menuGroup, menuGroup)
		.innerJoin(menu.grantedAuthorities, menuAuthority)
//		.innerJoin(menuAuthority.menu, menu)
		.innerJoin(menuAuthority.authority, userAuthority)
		.where(
			menu.position.eq(position)
			.and(menu.useStatus.eq(UseStatus.enable))
			.and(menuGroup.dimension.eq(dimension))
			.and(userAuthority.authority.in(grantedAuthorities)))
		.orderBy(menu.orders.asc())
		.limit(100)
		.list(menu);
	}
	
	
	@Override
	public List<Menu> findByAuthority(String authority) {
		QMenu menu = QMenu.menu;
		QMenuAuthority roleMenu = QMenuAuthority.menuAuthority;
		QUserAuthority role = QUserAuthority.userAuthority;
		
		return new JPAQuery(em).from(menu)
				.join(menu.grantedAuthorities, roleMenu)
				.join(roleMenu.authority, role)
				.where(role.authority.eq(authority))
				.orderBy(menu.orders.asc())
				.limit(100)
				.listResults(menu).getResults();
	}
}
