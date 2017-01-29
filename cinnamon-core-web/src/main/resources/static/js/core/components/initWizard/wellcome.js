angular.module('cinnamon.core')
.component('cmInitWizardWellcome', {
	templateUrl: '/core/components/initWizard/wellcome',
	controller: InitWizardWellcome
});

function InitWizardWellcome($scope, $mdStepper) {
	var ctrl = this;
	
	ctrl.next = function() {
		$mdStepper('initWizardStepper').next();
	}
}