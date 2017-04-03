package org.cinnamon.core.config;

import java.util.Comparator;
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
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private EntityManager em;
	
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
	public void execute() throws Exception {
		logger.info("start");
		
		Map<String, InitData> initDatas = ac.getBeansOfType(InitData.class);
		initDatas.values().stream().sorted(new Comparator<InitData>() {
			
			@Override
			public int compare(InitData o1, InitData o2) {
				if (o1.order() > o2.order()) {
					return 1;
				} else if (o1.order() < o2.order()) {
					return -1;
				} else {
					return 0;
				}
			}
		}).forEach(initData -> {
			try {
				initData.save(em);
			} catch (Exception e) {
				e.printStackTrace();
				
				//TODO 에러를 execute 밖으로 던질 수있어야 함.
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
