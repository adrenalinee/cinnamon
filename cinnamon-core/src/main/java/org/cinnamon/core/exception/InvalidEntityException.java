package org.cinnamon.core.exception;

/**
 * 
 * create date: 2015. 3. 25.
 * @author 동성
 *
 */
public class InvalidEntityException extends BadRequestException {
	
	private static final long serialVersionUID = -1923476312092440455L;
	
	private String entityClass;
	
	public InvalidEntityException(String message) {
		super(message);
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public String getErrorCode() {
		return "ER0001";
	}

	@Override
	public String getDescription() {
		return "유효하지 않습니다.";
	}
}
