package org.cinnamon.common.exceptions.vo;

import java.util.List;

/**
 * response status 404 wrapper
 * 
 * for invalid request parameter
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
public class InvalidParameterInfo extends ExceptionBaseInfo {
	
	List<Violation> violations;

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}
	
}
