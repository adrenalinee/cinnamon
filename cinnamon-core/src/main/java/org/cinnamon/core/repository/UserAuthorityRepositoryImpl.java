package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.Permission;
import org.cinnamon.core.domain.PermissionMenu;
import org.cinnamon.core.domain.QMenu;
import org.cinnamon.core.domain.QMenuGroup;
import org.cinnamon.core.domain.QPermission;
import org.cinnamon.core.domain.QPermissionMenu;
import org.cinnamon.core.domain.QUserGroup;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.vo.search.AuthoritySearch;
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
 * @since 2015. 2. 3.
 */
public class UserAuthorityRepositoryImpl extends QueryDslRepositorySupport implements UserAuthorityRepositoryCustom {
	
	public UserAuthorityRepositoryImpl() {
		super(Permission.class);
	}

	@Autowired
	EntityManager em;
	
	
	/**
	 * 
	 * @param userGroupId
	 * @param uri
	 * @return
	 */
	@Override
	public List<Permission> find(Long userGroupId, String uri) {
		QPermission role = QPermission.permission;
		QMenu menu = QMenu.menu;
		QPermissionMenu roleMenu = QPermissionMenu.permissionMenu;
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
	public List<PermissionMenu> getMenuAuthoritues(String authority, String uri) {
		QPermissionMenu roleMenu = QPermissionMenu.permissionMenu;
		
		JPAQuery query = new JPAQuery(em);
		query.from(roleMenu)
			.where(roleMenu.menu.uri.eq(uri).and(roleMenu.permission.authority.eq(authority)))
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
	public Page<Permission> search(AuthoritySearch permissionSearch, Pageable pageable) {
		QPermission role = QPermission.permission;
		
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
		
		JPAQuery query = new JPAQuery(em).from(role);
		query
			.where(builder);

		
		List<Permission> domains = getQuerydsl().applyPagination(pageable, query).list(role);
		long totalCount = query.count();
		
		Page<Permission> page = new PageImpl<Permission>(domains, pageable, totalCount);
		
		return page;
	}


	@Override
	public List<PermissionMenu> find(String authoritiy, Long menuGroupId) {
		QPermissionMenu roleMenu = QPermissionMenu.permissionMenu;
		QPermission role = QPermission.permission;
		QMenu menu = QMenu.menu;
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		
		JPAQuery query = new JPAQuery(em).from(roleMenu);
		
		return query
				.join(roleMenu.permission, role)
				.join(roleMenu.menu, menu)
				.join(menu.menuGroup, menuGroup)
				.where(
					role.authority.eq(authoritiy)
					.and(menuGroup.menuGroupId.eq(menuGroupId)))
				.list(roleMenu);
	}
	
	/**
	 * 메뉴 권한 정보 가져오기
	 * @author 정명성
	 * create date : 2016. 3. 4.
	 * @param permissionId
	 * @param menuGroupId
	 * @return
	 */
	public List<PermissionMenu> find(Long permissionId, Long menuGroupId) {
		QPermissionMenu permissionMenu = QPermissionMenu.permissionMenu;
		QPermission permission = QPermission.permission;
		QMenu menu = QMenu.menu;
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		
		JPAQuery query = new JPAQuery(em).from(permissionMenu);
		
		return query.join(permissionMenu.permission, permission)
				.join(permissionMenu.menu, menu)
				.join(menu.menuGroup, menuGroup)
				.where(
					permission.permissionId.eq(permissionId)
					.and(menuGroup.menuGroupId.eq(menuGroupId)))
				.list(permissionMenu);
	}
	
	/**
	 * 메뉴 수정 정보 저장
	 * @author 정명성
	 * create date : 2016. 3. 7.
	 * @param permissionMenu
	 */
	public void savePermissionMenu(PermissionMenu permissionMenu) {
		em.persist(permissionMenu);
	}


	@Override
	public Permission findFirst1ByAuthorityIn(List<String> authorities) {
		QPermission permission = QPermission.permission;
		JPAQuery query = new JPAQuery(em).from(permission);
		Permission result = query.where(permission.authority.in(authorities), permission.defaultMenu.isNotNull())
				.limit(1L).singleResult(permission);
		return result;
	}
}
