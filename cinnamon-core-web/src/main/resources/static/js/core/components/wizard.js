angular.module('cinnamon')
.component('cmWizard', {
	templateUrl: '/core/components/wizard',
	controller: WizrdController
});

function WizrdController($scope, $http) {
	$http.get('/initWizard')
	.success(function(data) {
		console.log(data);
		$scope.wizard = data;
	});
}