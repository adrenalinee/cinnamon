package org.cinnamon.core.init.builder;

import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.enumeration.DefinedUserAuthority;


public class BuilderTest {
	
	public static void main(String[] args) {
		SiteBuilder siteBuilder0 = new SiteBuilder();
		siteBuilder0.role(DefinedUserAuthority.systemMaster.name())
			.name("시스템 최고 운영 권한")
			.userGroup("시스템 최고 운영자 그룹")
		.role(DefinedUserAuthority.normal.name())
			.name("기본 권한")
			.userGroup("일반 사용자 그룹");
			
		
		
		
		SiteBuilder siteBuilder = new SiteBuilder();
		siteBuilder.site("console")
			.menuGroup("configuration", MenuPosition.sidebar)
			.name("설정")
			.role(DefinedUserAuthority.systemMaster.name())
			.permitElse()
				.menu("운영자", "/configuration/users")
				.iconClass("fa fa-user")
					.role("systemMaster")
					.permitElse()
					.role("normalUser")
					.permitElse()
			.and()
				.menu("사용자 모임", "/configuration/userGroups")
				.iconClass("fa fausers")
			.and()
				.menu("이메일")
				.iconClass("fa fa-envelope")
					.child("서버", "/configuration/emailServers")
					.iconClass("fa fa-server")
				.parent()
					.child("/configuration/emailTemplates")
					.name("템플릿")
					.iconClass("fa fa-envelope-o")
			.and()
				
				
				;
		
		siteBuilder.build();
		
	}
}
