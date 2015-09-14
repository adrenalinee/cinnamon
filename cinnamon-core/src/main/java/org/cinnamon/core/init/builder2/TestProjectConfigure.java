package org.cinnamon.core.init.builder2;

import static org.cinnamon.core.init.builder2.ProjectBuilder.*;

import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.enumeration.DefinedUserAuthority;


public class TestProjectConfigure {
	
	
	public static void main(String[] args) {
		ProjectBuilder projectBuilder = new ProjectBuilder();
		
		projectBuilder.addSite(
			site("콘솔", "console").addMenuGroup(
				menuGroup("기본 설정 메뉴", "configuration").addMenus(
					menu("사용자", MenuPosition.sidebar)
						.uri("/configuration/users")
						.iconClass("glyphicon glyphicon-user")
						.addGrantedAuthority(DefinedUserAuthority.systemMaster.name())
				).addMenus(
					menu("사용자 모임", MenuPosition.sidebar)
						.uri("/configuration/userGroups")
						.iconClass("fa fa-users")
						.addGrantedAuthority(DefinedUserAuthority.systemMaster.name())
				)
			)
		).addRole(
			role("시스템 최고 운영자", DefinedUserAuthority.systemMaster.name())
		);
		
		
		projectBuilder.print();
//		projectBuilder.build();
	}
}
