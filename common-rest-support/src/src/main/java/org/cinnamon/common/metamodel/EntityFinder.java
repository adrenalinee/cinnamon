package org.cinnamon.common.metamodel;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.cinnamon.common.metamodel.vo.Column;
import org.cinnamon.common.metamodel.vo.EntityCategory;
import org.cinnamon.common.metamodel.vo.EntityDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * create date: 2015. 4. 14.
 * @author 동성
 *
 */
@Component
public class EntityFinder {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	EntityManager em;
	
	
	/**
	 * 
	 * @return
	 */
	public List<EntityCategory> getCategories() {
		logger.info("start");
		
		Metamodel m = em.getMetamodel();
		
		Map<String, EntityCategory> categoriesMap = new LinkedHashMap<String, EntityCategory>();
		
		
		m.getEntities().forEach(entity -> {
			EntityDefinition entityDefinition =  new EntityDefinition();
			entityDefinition.setName(entity.getName());
			entityDefinition.setClassName(entity.getJavaType().getName());
			
			
			String categoryName = entity.getJavaType().getPackage().getName();
//			System.out.println("categoryName: " + categoryName);
			
			
			EntityCategory category = categoriesMap.get(categoryName);
			if (category == null) {
				List<EntityDefinition> entities = new LinkedList<EntityDefinition>();
				entities.add(entityDefinition);
				
				category = new EntityCategory();
				category.setName(categoryName);
				category.setEntities(entities);
				categoriesMap.put(categoryName, category);
			} else {
				category.getEntities().add(entityDefinition);
			}
			
		});
		
		
		List<EntityCategory> entityCategories = new LinkedList<EntityCategory>();
		for (EntityCategory entityCategory: categoriesMap.values()) {
			Comparator<EntityDefinition> comparator = (o1, o2) -> {
				return o1.getName().compareTo(o2.getName());
			};
			
			entityCategory.getEntities().sort(comparator);
			
			entityCategories.add(entityCategory);
		}
		
		
		return entityCategories;
	}
	
	
	public EntityDefinition getDefinition(String entityClass) throws Exception {
		logger.info("start");
		
		Metamodel m = em.getMetamodel();
		
		EntityType<?> entityType = m.entity(Class.forName(entityClass));
		
		EntityDefinition entityDefinition =  new EntityDefinition();
		entityDefinition.setName(entityType.getName());
		entityDefinition.setColumns(getColumns(entityType));
		
		
		return entityDefinition;
	}
	
	
	private List<Column> getColumns(EntityType<?> entityType) {
		List<Column> columns = new LinkedList<Column>();
		
		entityType.getAttributes().forEach(attribute -> {
			Column column = new Column();
			column.setName(attribute.getName());
			column.setAttributeType(attribute.getPersistentAttributeType().name());
			column.setJavaType(attribute.getJavaType().getSimpleName());
			
			columns.add(column);
		});
		
		
		return columns;
	}
}
