angular.module('cinnamon')
.controller('configuration.users.list', function($scope, $http) {
	console.log('configuration.users.list');
	
	$http.get('/rest/configuration/users')
	.success(function(data) {
		console.log(data);
		
		$scope.domains = data;
	});
});