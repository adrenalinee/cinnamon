angular.module('cinnamon')
.controller('configuration.users.list', function($scope, $http) {
	console.log('configuration.users.list');
	
	$scope.selectedUsers = [];
	
	$http.get('/rest/configuration/users')
	.success(function(data) {
		console.log(data);
		
		$scope.domains = data;
	});
	
	
	$scope.onSelectUser = function() {
		console.log('onSelectUser');
		
	}
});