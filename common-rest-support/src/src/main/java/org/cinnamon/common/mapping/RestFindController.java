package org.cinnamon.common.mapping;

import java.util.List;

import org.cinnamon.common.mapping.vo.ApiDefinition;
import org.cinnamon.common.mapping.vo.ApiCategory;
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
public class RestFindController {
	
	
	
	@RequestMapping(value="apis", method=RequestMethod.GET)
	public List<ApiCategory> apis() {
		
		return null;
	}
	
	
	@RequestMapping(value="apis/{categoryName}", method=RequestMethod.GET)
	public ApiCategory categoryDefinetion(@PathVariable String categoryName) {
		
		return null;
	}
	
//	@RequestMapping(value="apis/{categoryName}", method=RequestMethod.GET)
//	public APIDefinition apiCategoryApis(@PathVariable String categoryName) {
//		
//		
//		return null;
//	}
	
	
	@RequestMapping(value="apis/{categoryName}/{apiName}", method=RequestMethod.GET)
	public ApiDefinition apiDefinition(@PathVariable String categoryName, @PathVariable String apiName) {
		
		
		return null;
	}
	
}
