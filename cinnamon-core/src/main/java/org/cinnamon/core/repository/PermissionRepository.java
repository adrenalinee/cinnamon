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
import org.cinnamon.core.vo.search.PermissionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 
 * @author 동성
 * @since 2015. 2. 3.
 */
@Repository
public class PermissionRepository {
	
	@Autowired
	EntityManager em;
	
	
	public void persist(Permission permission) {
		em.persist(permission);
	}

	
	/**
	 * 
	 * @param userGroupId
	 * @param uri
	 * @return
	 */
	public List<Permission> find(Long userGroupId, String uri) {
		QPermission permission = QPermission.permission;
		QMenu menu = QMenu.menu;
		QPermissionMenu permissionMenu = QPermissionMenu.permissionMenu;
		QUserGroup userGroup = QUserGroup.userGroup;
		
		
		JPAQuery query = new JPAQuery(em);
		query.from(permission, permissionMenu, menu, userGroup)
//			.innerJoin(permission.permissionId, permissionMenu.permissionId)
			.innerJoin(permissionMenu)
			.innerJoin(menu)
			.innerJoin(userGroup)
			.where(menu.uri.eq(uri).and(userGroup.userGroupId.eq(userGroupId)));
		
		return query.list(permission);
	}
	
	
	/**
	 * 
	 * @param authority
	 * @param uri
	 * @return
	 */
	public List<PermissionMenu> getPermissionMenus(String authority, String uri) {
		QPermissionMenu permissionMenu = QPermissionMenu.permissionMenu;
		
		JPAQuery query = new JPAQuery(em);
		query.from(permissionMenu)
			.where(permissionMenu.menu.uri.eq(uri).and(permissionMenu.permission.authority.eq(authority)))
			.limit(100);
		
		
		return query.list(permissionMenu);
	}
	
	
	/**
	 * 
	 * @param permissionSearch
	 * @param pageable
	 * @return
	 */
	public Page<Permission> find(PermissionSearch permissionSearch, Pageable pageable) {
		QPermission permission = QPermission.permission;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (permissionSearch.getPermissionId() != null) {
			builder.and(permission.permissionId.eq(permissionSearch.getPermissionId()));
		}
		if (!StringUtils.isEmpty(permissionSearch.getName())) {
			builder.and(permission.name.like("%" + permissionSearch.getName() + "%"));
		}
		if (!StringUtils.isEmpty(permissionSearch.getAutority())) {
			builder.and(permission.authority.like("%" + permissionSearch.getAutority() + "%"));
		}
		if (!StringUtils.isEmpty(permissionSearch.getUseStatus())) {
			builder.and(permission.useStatus.eq(UseStatus.valueOf(permissionSearch.getUseStatus())));
		}
		
		
		long offset = pageable.getOffset();
		long limit = pageable.getPageSize();
		
		JPAQuery query = new JPAQuery(em).from(permission);
		query
			.where(builder)
			.offset(offset)
			.limit(limit)
			.orderBy(permission.permissionId.desc());
		
		
		List<Permission> domains = query.list(permission);
		long totalCount = query.count();
		
		Page<Permission> page = new PageImpl<Permission>(domains, pageable, totalCount);
		
		return page;
	}


	public Permission findById(Long permissionId) {
		return em.find(Permission.class, permissionId);
	}
	
	
	public Permission findByAuthority(String authority) {
		QPermission permission = QPermission.permission;
		
		JPAQuery query = new JPAQuery(em).from(permission);
		
		return query.where(permission.authority.eq(authority)).singleResult(permission);
	}


	public List<Permission> findAll() {
		return em.createQuery("from Permission", Permission.class).getResultList();
	}


	public void persist(PermissionMenu permissionMenu) {
		em.persist(permissionMenu);
	}


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
	
	
	
	
	
}
