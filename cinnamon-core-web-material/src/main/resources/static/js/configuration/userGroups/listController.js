angular.module('cinnamon')
.controller('configuration.userGroups.list', function($scope, $http) {
	console.log('configuration.userGroups.list');
	
	$http.get('/rest/configuration/userGroups')
	.success(function(data) {
		console.log(data);
		
		$scope.domains = data;
	});
	
});