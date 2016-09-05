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

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * 
 * @author 동성
 * @since 2015. 2. 3.
 */
public class UserAuthorityRepositoryImpl extends QueryDslRepositorySupport implements UserAuthorityRepositoryCustom {

	@Autowired
	EntityManager em;
	
	public UserAuthorityRepositoryImpl() {
		super(Permission.class);
	}
	
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
		
		return from(role, roleMenu, menu, userGroup)
				.innerJoin(roleMenu)
				.innerJoin(menu)
				.innerJoin(userGroup)
				.where(menu.uri.eq(uri).and(userGroup.userGroupId.eq(userGroupId)))
				.select(role)
				.fetch();
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
		
		return from(roleMenu)
				.where(roleMenu.menu.uri.eq(uri).and(roleMenu.permission.authority.eq(authority)))
				.limit(100)
				.fetch();
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
		
		JPQLQuery<Permission> query = from(role).where(builder);
		
		List<Permission> domains = getQuerydsl().applyPagination(pageable, query).fetch();
		long totalCount = query.fetchCount();
		
		return new PageImpl<Permission>(domains, pageable, totalCount);
	}


	@Override
	public List<PermissionMenu> find(String authoritiy, Long menuGroupId) {
		QPermissionMenu roleMenu = QPermissionMenu.permissionMenu;
		QPermission role = QPermission.permission;
		QMenu menu = QMenu.menu;
		QMenuGroup menuGroup = QMenuGroup.menuGroup;
		
		return from(roleMenu)
				.join(roleMenu.permission, role)
				.join(roleMenu.menu, menu)
				.join(menu.menuGroup, menuGroup)
				.where(
					role.authority.eq(authoritiy)
					.and(menuGroup.menuGroupId.eq(menuGroupId)))
				.fetch();
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
		
		return from(permissionMenu)
				.join(permissionMenu.permission, permission)
				.join(permissionMenu.menu, menu)
				.join(menu.menuGroup, menuGroup)
				.where(
					permission.permissionId.eq(permissionId)
					.and(menuGroup.menuGroupId.eq(menuGroupId)))
				.fetch();
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
		return from(permission).where(permission.authority.in(authorities), permission.defaultMenu.isNotNull())
				.limit(1L).fetchOne();
	}
}
