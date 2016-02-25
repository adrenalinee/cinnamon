package org.cinnamon.core.config;

import java.util.Date;

import org.cinnamon.core.domain.Property;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.enumeration.DefinedDBProperty;
import org.cinnamon.core.repository.PropertyRepository;
import org.cinnamon.core.service.UserBaseService;
import org.cinnamon.core.vo.UserJoinVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * created date: 2015. 9. 17.
 * @author 신동성
 */
@Service
public class SystemConfigureService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ApplicationContext ac;
	
//	@Autowired
//	BaseDataBuilder baseDataBuilder;
	
	@Autowired
	UserBaseService<UserBase> userService;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	InitDataManager initDataManager;
	
	/**
	 * 시스템 초기화 작업이 최종 마무리 되었는지 알려준다.
	 * 
	 * @return
	 */
	@Transactional
	public boolean isInitialized() {
		Property initProperty = propertyRepository.findByName(DefinedDBProperty.initialize_complate.name());
		if (initProperty == null) {
			return false;
		}
		
		return initProperty.getBooleanValue();
	}
	
	
	/**
	 * 초기화가 진행중일 경우 다음으로진행할 스탭이 어디인지 알려준다.
	 * 
	 * @return
	 */
	@Transactional
	public String findNextInitializeStep() {
		Property initProperty = propertyRepository.findByName(DefinedDBProperty.initialize_complate.name());
		if (initProperty != null && initProperty.getBooleanValue()) {
			//이미 초기화 되어 있음
			return null;
		}
		
		initProperty = propertyRepository.findByName(DefinedDBProperty.initialize_baseData.name());
		if (initProperty == null || !initProperty.getBooleanValue()) {
			return "";
//			return "baseData";
		}
		
		initProperty = propertyRepository.findByName(DefinedDBProperty.initialize_firstUser.name());
		if (initProperty == null || !initProperty.getBooleanValue()) {
			return "firstUser";
		}
		
		return "";
	}
	
	
	/**
	 * 시스템 기본 데이터 생성
	 */
	@Transactional
	public void createBaseData() throws Exception {
		logger.info("start");
		
//		Map<String, SystemConfigurer> systemConfigurers = ac.getBeansOfType(SystemConfigurer.class);
//		systemConfigurers.forEach((name, systemConfigurer) -> {
//			systemConfigurer.configure(baseDataBuilder);
//		});
//		
//		baseDataBuilder.print();
//		baseDataBuilder.build();
		
		initDataManager.execute();
		
		saveProperty(DefinedDBProperty.initialize_baseData, true);
	}
	
	
//	/**
//	 * 
//	 */
//	public void createInitData() throws Exception {
//		initDataManager.execute();
//	}
	
	
	
	/**
	 * 최초 사용자는 가입 승인이 되어 있다.
	 * 
	 * @param userVo
	 */
	@Transactional
	public void joinFirstSystemMaster(UserJoinVo userVo) {
		logger.info("start");
		
		UserBase user = userService.joinSystemMaster(userVo);
		user.setAccepted(true);
		user.setAcceptedAt(new Date());
		
		saveProperty(DefinedDBProperty.initialize_firstUser, true);
		saveProperty(DefinedDBProperty.initialize_complate, true);
	}
	
	
	private void saveProperty(DefinedDBProperty name, Boolean value) {
		Property property = new Property();
		property.setName(name.name());
		property.setValue(value.toString());
		
		propertyRepository.save(property);
	}
}
