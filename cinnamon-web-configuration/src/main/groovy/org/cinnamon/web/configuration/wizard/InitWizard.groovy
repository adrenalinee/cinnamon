package org.cinnamon.web.configuration.wizard

import org.cinnamon.core.wizard.Wizard
import org.cinnamon.core.wizard.WizardBuilder;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@Component
class InitWizard {
	
	Wizard wizard
	
	Wizard createInitWizard() {
		WizardBuilder wizardBuilder = new WizardBuilder()
		
		wizard = wizardBuilder
			.setName("시스템 초기화 마법사")
			.setDescription("시스템 설치후 최초 접속할때 각정 시스템 상태 초기화 작업 진행")
			.addStep("시스템 초기화 시작", "/configuration/initWizard/welcome", "시스템 시작 환영 메시지")
			.addStep("기본 데이터 생성", "/configuration/initWizard/baseData", "시스템 시작에 필요한 기본 데이터 생성")
			.addStep("최초사용자 생성", "/configuration/initWizard/firstUser", "시스템에 로그인할 최고 권한을 가진 최초의 사용자 계정생성").build()
		
		wizard
	}
}
