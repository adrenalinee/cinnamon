package org.cinnamon.core.config;

import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author 동성
 * @since 2015. 1. 7.
 */
@Component
public class InitDataManager {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ApplicationContext ac;
	
	@Autowired
	EntityManager em;
	
//	@Autowired
//	private PlatformTransactionManager transactionManager;
//	
//	private TransactionTemplate transactionTemplate;
//	
//	@PostConstruct
//	public void postConstruct() {
//		transactionTemplate = new TransactionTemplate(transactionManager);
//	}
	
	
	@Transactional
	public void execute() {
		logger.info("start");
		
		Map<String, InitData> initDatas = ac.getBeansOfType(InitData.class);
		initDatas.forEach((name, initData) -> {
			try {
				initData.save(em);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
//		transactionTemplate.execute(action -> {
//			initDatas.forEach((name, initData) -> {
//				try {
//					initData.save(em);
//				} catch (Exception e) {
//					e.printStackTrace();
//					action.setRollbackOnly();
//				}
//			});
//			
//			return initDatas.size();
//		});
	}
}
