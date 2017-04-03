angular.module('cinnamon')
.controller('welcomeCtrl', function($scope, $mdStepper) {
	
	$scope.next = function() {
		console.log('next');
		$mdStepper('initWizardStepper').next();
	}
});