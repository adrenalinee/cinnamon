angular.module('cinnamon')
.component('cmWizard', {
	templateUrl: '/core/components/cmWizard',
	controller: WizrdController
});

function WizrdController($scope, $http, $mdStepper) {
	var ctrl = this;
	
	$http.get('/rest/configuration/initWizard')
	.success(function(data) {
		console.log(data);
		$scope.wizard = data;
	});
	
	
	ctrl.next = function() {
		$mdStepper('initWizardStepper').next();
	}
}