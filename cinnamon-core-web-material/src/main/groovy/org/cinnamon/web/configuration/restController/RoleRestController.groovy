package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.domain.Permission
import org.cinnamon.core.domain.PermissionMenu
import org.cinnamon.core.service.RoleService
import org.cinnamon.core.vo.PermissionMenuVo
import org.cinnamon.core.vo.PermissionVo
import org.cinnamon.core.vo.search.AuthoritySearch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping(value="/rest/configuration/roles")
class RoleRestController {
	
	@Autowired
	RoleService roleService;
	
	/**
	 * 권한 목록 조회
	 * @author 정명성
	 * create date : 2016. 3. 3.
	 * @param permissionSearch
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<Permission> list (AuthoritySearch permissionSearch, Pageable pageable) {
		 return roleService.search(permissionSearch, pageable);
	}
	
	/**
	 * 권한 상세 조회
	 * @author 정명성
	 * create date : 2016. 3. 3.
	 * @param permissionId
	 * @return
	 */
	@RequestMapping(value="{permissionId}", method=RequestMethod.GET)
	Permission view (@PathVariable Long permissionId) {
		return roleService.getPermission(permissionId);
	}
	
	/**
	 * 권한 정보 생성
	 * @author 정명성
	 * create date : 2016. 3. 3.
	 * @param permissionVo
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	def create (@Valid @RequestBody PermissionVo permissionVo, UriComponentsBuilder builder) {
		Permission permission = roleService.createPermission(permissionVo);
		/*
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				RoleRestController.class,
				"view",
				permission.getPermissionId())
			.build()
			.toUri()
		*/
		URI location = builder.path("/configuration/roles/{permissionId}").buildAndExpand(permission.getPermissionId()).toUri()
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * 권한 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 3.
	 * @param permissionVo
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.PUT)
	ResponseEntity<Void> put(@Valid @RequestBody PermissionVo permissionVo) {
		roleService.updatePermission(permissionVo);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="{permissionId}/menus")
	Map<Long, PermissionMenu> permissionMenus(@PathVariable Long permissionId, Long menuGroupId) {
		return roleService.getPermissionMenus(permissionId, menuGroupId);
	}
	
	/**
	 * 메뉴 권한 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 7.
	 * @param permissionId
	 * @param permissionMenuVo
	 * @param menuGroupId
	 * @throws Exception
	 */
	@RequestMapping(value="{permissionId}/menus/{menuGroupId}", method=RequestMethod.PUT)
	public void put(@PathVariable Long permissionId,
							@Valid @RequestBody List<PermissionMenuVo> permissionMenuVo,
							@PathVariable Long menuGroupId) throws Exception {
		
		roleService.modifyMenu(permissionId, permissionMenuVo, menuGroupId);
	}
}
