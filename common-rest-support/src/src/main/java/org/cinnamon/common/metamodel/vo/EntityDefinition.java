package org.cinnamon.common.metamodel.vo;

import java.util.List;

/**
 * 
 * create date: 2015. 4. 13.
 * @author 동성
 *
 */
public class EntityDefinition {
	
	private String name;
	
	private String className;
	
	private String description;
	
	private List<Column> columns;

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

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
