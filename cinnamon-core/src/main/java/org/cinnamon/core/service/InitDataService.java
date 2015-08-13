package org.cinnamon.core.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.cinnamon.core.domain.Property;
import org.cinnamon.core.init.InitDataManager;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 동성
 * @since 2015. 1. 7.
 */
@Service
public class InitDataService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	InitDataManager initDataManager;
	
	@Autowired
	EntityManager em;
	
	
	@Transactional
	public void init() {
		logger.info("start");
		
		boolean initError = false;
		
		initDataManager.stream()
			.forEach(initData -> {
				//TODO 개별 트렌젝션 처리해줄것
				try {
					initData.save(em);
				} catch (Exception e) {
					e.printStackTrace();
					//TODO 모든 에러를 묶어서 밖으로 전달해야 함.
				}
			});
		
		if (initError) {
			logger.warn("초기화 데이터를 생성중에 오류가발생하였습니다.");
			return;
		}
		
		initDataManager.clear();
		
		Property property = new Property();
		property.setName("initialize");
		property.setValue("true");
		
		propertyRepository.save(property);
	}
	
	
//	@Transactional
//	public void createFirstAdmin(AdministratorVo administratorVo) {
//		logger.info("start");
//		
//		Administrator administrator = new Administrator();
//		BeanUtils.copyProperties(administratorVo, administrator);
//		
//		
//		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
//		administrator.setPassword(e.encode(administrator.getPassword()));
//		em.persist(administrator);
//		
//		
//		Permission permission = new Permission();
//		permission.setName("시스템관리권한");
//		permission.setAuthority("ROLE_ADMIN");
//		em.persist(permission);
//		
////		administrator.getPermissions().add(permission);
//		
//		
//		AdminGroup adminGroup = new AdminGroup();
//		adminGroup.setName("최고 관리자 그룹");
//		adminGroup.setPermission(permission);
//		em.persist(adminGroup);
//		
//		administrator.getAdminGroups().add(adminGroup);
//		
//		
//		List<Menu> menus = menuRepository.findAll();
//		menus.forEach(menu -> {
//			PermissionMenu permissionMenu = new PermissionMenu();
//			permissionMenu.setPermission(permission);
//			permissionMenu.setMenu(menu);
//			
//			em.persist(permissionMenu);
//		});
//		
//		
//		AdminActivity adminActivity = new AdminActivity();
//		adminActivity.setAdminId(administrator.getAdminId());
//		adminActivity.setType(AdminActivityType.join);
//		
//		em.persist(adminActivity);
//	}
}
