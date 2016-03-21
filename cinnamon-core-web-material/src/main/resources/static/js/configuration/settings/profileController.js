angular.module('cinnamon')
.controller('configuration.settings.profile', function($scope, $http, $interval, $stateParams, $mdDialog) {
	console.log('configuration.settings.profile');
	
	var userId = $stateParams.userId;
	$scope.userId = userId;
	
	$http.get('/rest/configuration/users/' + userId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$http.get("/rest/configuration/groups/nations/childs")
	.success(function(data) {
		console.log(data);
		$scope.nations = data;
		
		$scope.nations.sort(function(a, b) {
			return a.name > b.name ? 1 : a.name < b.name ? -1: 0;
		});
	});
	
});