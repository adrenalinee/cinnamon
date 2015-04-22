package org.cinnamon.common.metamodel;

import java.util.List;

import org.cinnamon.common.metamodel.vo.EntityCategory;
import org.cinnamon.common.metamodel.vo.EntityDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * create date: 2015. 4. 14.
 * @author 동성
 *
 */
@RestController
@RequestMapping("/cinnamon")
public class EntityFindController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EntityFinder entityFinder;
	
	
	@RequestMapping(value="entities", method=RequestMethod.GET)
	public List<EntityCategory> entities() {
		logger.info("start");
		
		return entityFinder.getCategories();
	}
	
	
	
	@RequestMapping(value="entities/{category}", method=RequestMethod.GET)
	public EntityDefinition entityDefinition(@PathVariable String category, String entityClass) throws Exception {
		logger.info("start");
		
		
		return entityFinder.getDefinition(entityClass);
	}
}
