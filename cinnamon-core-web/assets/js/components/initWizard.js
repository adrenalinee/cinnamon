angular.module('cinnamon.core')
.component('initWizard', {
	templateUrl: 'core/components/initWizard',
	controller: InitWizardController
});

function InitWizardController($scope, $http) {
	
	$http.get('/initWizard')
	.success(function(data) {
		console.log(data);
		$scope.wizard = data;
	});
}