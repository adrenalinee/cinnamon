package org.cinnamon.common.metamodel.vo;

/**
 * 
 * create date: 2015. 4. 13.
 * @author 동성
 *
 */
public class Column {
	
	private String name;
	
	private String description;
	
	private String javaType;
	
	private String attributeType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
