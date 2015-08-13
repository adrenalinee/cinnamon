package org.cinnamon.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 동성
 * @since 2015. 1. 27.
 */
public class UseStatusVo {
	
	@NotEmpty
	String useStatus;

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
}
