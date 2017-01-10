angular.module('cinnamon.core')
.config(function($httpProvider) {
	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
});
angular.module('cinnamon.core')
.component('appMain', {
	templateUrl: 'core/components/appMain',
	controller: appMainController
});

function appMainController() {
	
}
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