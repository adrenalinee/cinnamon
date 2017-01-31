angular.module('cinnamon')
.controller('databaseCtrl', function($scope, $mdStepper) {
	
	$scope.next = function() {
		console.log('next');
		$mdStepper('initWizardStepper').next();
	}
});