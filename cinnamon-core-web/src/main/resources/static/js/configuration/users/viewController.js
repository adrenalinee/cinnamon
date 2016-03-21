angular.module('cinnamon')
.controller('configuration.users.view', function($scope, $http, $stateParams) {
	console.log('configuration.users.view');
	
	var userId = $stateParams.userId;
	
	$http.get('/rest/configuration/users/' + userId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
});