package org.cinnamon.common.metamodel.vo;

import java.util.List;

/**
 * 
 * create date: 2015. 4. 14.
 * @author 동성
 *
 */
public class EntityCategory {
	
	private String name;
	
	private String description;
	
	private List<EntityDefinition> entities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EntityDefinition> getEntities() {
		return entities;
	}

	public void setEntities(List<EntityDefinition> entities) {
		this.entities = entities;
	}
	
}
