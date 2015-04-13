package org.cinnamon.common.mapping;

import java.util.List;

import org.cinnamon.common.mapping.vo.ApiCategory;
import org.cinnamon.common.mapping.vo.ApiDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 동성
 * @since 2015. 2. 27.
 */
@RestController
@RequestMapping("/cinnamon")
public class RestFindController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApiFinder apiFinder;
	
	
	@RequestMapping(value="apis", method=RequestMethod.GET)
	public List<ApiCategory> apis() {
		logger.info("start");
		
		return apiFinder.getCategories();
	}
	
	
//	@RequestMapping(value="apis/{categoryName}", method=RequestMethod.GET)
//	public ApiCategory categoryDefinetion(@PathVariable String categoryName) {
//		logger.info("start");
//		
//		return null;
//	}
	
//	@RequestMapping(value="apis/{categoryName}", method=RequestMethod.GET)
//	public APIDefinition apiCategoryApis(@PathVariable String categoryName) {
//		
//		
//		return null;
//	}
	
	
	@RequestMapping(value="apis/{category}", method=RequestMethod.GET)
	public ApiDefinition apiDefinition(@PathVariable String category, String apiName, String requestMethod) {
		logger.info("start");
		
		return apiFinder.getDefinition(category, apiName, requestMethod);
	}
	
}
