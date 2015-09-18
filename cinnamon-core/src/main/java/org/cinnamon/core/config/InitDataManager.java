package org.cinnamon.core.config;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

/**
 * 
 * @author 동성
 * @since 2015. 1. 7.
 */
@Component
public class InitDataManager {
	
	List<InitData> initDatas = new LinkedList<InitData>();
	
//	@PostConstruct
//	public void commonData() {
//		initDatas.add(new GroupInitData());
//	}
	
	public void add(InitData initData) {
		initDatas.add(initData);
	}
	
	public Stream<InitData> stream() {
		return initDatas.stream();
	}
	
	
	/**
	 * 최초에 한번 실행하면 더이상 실행할 일이 없기때문에 메모리 낭비를 줄이기 위해
	 * 초기화 인스턴스들은 지워준다.
	 */
	public void clear() {
		initDatas = null;
	}
}
