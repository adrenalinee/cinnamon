angular.module('cinnamon')
.component('cmWizard', {
	templateUrl: '/core/components/cmWizard',
	controller: WizardController,
	bindings: {
		infoUri: '@'
	}
});

function WizardController($scope, $http, $mdStepper) {
	var ctrl = this;
	
	ctrl.$onInit = function() {
		console.log(ctrl.infoUri);
		
		$http.get(ctrl.infoUri)
		.then(function(response) {
			console.log(response);
			$scope.wizard = response.data;
		});
	}
	
	
}