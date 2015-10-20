package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.MenuAuthority;
import org.cinnamon.core.domain.QMenu;
import org.cinnamon.core.domain.QMenuAuthority;
import org.cinnamon.core.domain.QMenuGroup;
import org.cinnamon.core.domain.QUserAuthority;
import org.cinnamon.core.domain.QUserGroup;
import org.cinnamon.core.domain.UserAuthority;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.vo.search.RoleSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * @author 동성
 * @since 2015. 2. 3.
 */
public class RoleRepositoryImpl implements RoleRepositoryCustom {
	
	@Autowired
	EntityManager em;
	
	
	/**
	 * 
	 * @param userGroupId
	 * @param uri
	 * @return
	 */
	@Override
	public List<UserAuthority> find(Long userGroupId, String uri) {
		QUserAuthority role = QUserAuthority.userAuthority;
		QMenu menu = QMenu.menu;
		QMenuAuthority roleMenu = QMenuAuthority.menuAuthority;
		QUserGroup userGroup = QUserGroup.userGroup;
		
		
		JPAQuery query = new JPAQuery(em);
		query.from(role, roleMenu, menu, userGroup)
//			.innerJoin(permission.permissionId, permissionMenu.permissionId)
			.innerJoin(roleMenu)
			.innerJoin(menu)
			.innerJoin(userGroup)
			.where(menu.uri.eq(uri).and(userGroup.userGroupId.eq(userGroupId)));
		
		return query.list(role);
	}
	
	
	/**
	 * 
	 * @param authority
	 * @param uri
	 * @return
	 */
	@Override
	public List<MenuAuthority> getRoleMenus(String authority, String uri) {
		QMenuAuthority roleMenu = QMenuAuthority.menuAuthority;
		
		JPAQuery query = new JPAQuery(em);
		query.from(roleMenu)
			.where(roleMenu.menu.uri.eq(uri).and(roleMenu.role.authority.eq(authority)))
			.limit(100);
		
		return query.list(roleMenu);
	}
	
	
	/**
	 * 
	 * @param permissionSearch
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<UserAuthority> search(RoleSearch permissionSearch, Pageable pageable) {
		QUserAuthority role = QUserAuthority.userAuthority;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (!StringUtils.isEmpty(permissionSearch.getName())) {
			builder.and(role.name.like("%" + permissionSearch.getName() + "%"));
		}
		if (!StringUtils.isEmpty(permissionSearch.getAutority())) {
			builder.and(role.authority.like("%" + permissionSearch.getAutority() + "%"));
		}
		if (!StringUtils.isEmpty(permissionSearch.getUseStatus())) {
			builder.and(role.useStatus.eq(UseStatus.valueOf(permissionSearch.getUseStatus())));
		}
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(role);
		query
			.where(builder)
			.offset(offset)
			.limit(limit)
			.orderBy(role.authority.desc());
		
		
		List<UserAuthority> domains = query.list(role);
		long totalCount = query.count();
		
		Page<UserAuthority> page = new PageImpl<UserAuthority>(domains, pageable, totalCount);
		
		return page;
	}


	@Override
	public List<MenuAuthority> find(String authoritiy, Long menuGroupId) {
		QMenuAuthority roleMenu = QMenuAuthority.menuAuthority;
		QUserAuthority role = QUserAuthority.userAuthority;
		QMenu menu = QMenu.menu;
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		
		JPAQuery query = new JPAQuery(em).from(roleMenu);
		
		return query
				.join(roleMenu.role, role)
				.join(roleMenu.menu, menu)
				.join(menu.menuGroup, menuGroup)
				.where(
					role.authority.eq(authoritiy)
					.and(menuGroup.menuGroupId.eq(menuGroupId)))
				.list(roleMenu);
	}
	
	
	
	
	
}
