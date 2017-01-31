angular.module('cinnamon')
.controller('wellcomeCtrl', function($scope, $mdStepper) {
	
	$scope.next = function() {
		console.log('next');
		$mdStepper('initWizardStepper').next();
	}
});