package org.cinnamon.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.embeddable.ActivityParam;


/**
 * 사용자 활동
 * 
 * @author 동성
 * @since 2015. 1. 7.
 */
@Entity
public class UserActivity {
	
	@Id
	@GeneratedValue
	Long userActivityId;
	
	
	/**
	 * 활동한 운영자의 아이디
	 */
	String userId;
	
	/**
	 * 활동 종류
	 */
	@Column(nullable=false)
	String type;
	
	/**
	 * 액션의 상세 내용에 대한 파라미터 (json 값으로 여러 파라미터 저장)
	 * 포함되는 파라미터
	 * uri: 해당액션이 일어난 경로
	 * id: 해당액션에서 컨트롤한 도메인 아이디
	 * 
	 */
//	String param;
	
	ActivityParam activityParam;
	
	
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ActivityParam getActivityParam() {
		return activityParam;
	}

	public void setActivityParam(ActivityParam activityParam) {
		this.activityParam = activityParam;
	}

	public Long getUserActivityId() {
		return userActivityId;
	}

	public void setUserActivityId(Long userActivityId) {
		this.userActivityId = userActivityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
