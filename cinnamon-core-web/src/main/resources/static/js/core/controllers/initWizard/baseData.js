angular.module('cinnamon')
.controller('baseDataCtrl', function($scope, $http, $mdStepper) {
	
	$scope.createBaseData = function() {
		console.log('createBaseData');
		
		$scope.isProcess = true;
		$http.post('/rest/configuration/initWizard/baseData')
		.then(function(response) {
			$mdStepper('initWizardStepper').next();
		}).finally(function() {
			$scope.isProcess = false;
		});
	}
});