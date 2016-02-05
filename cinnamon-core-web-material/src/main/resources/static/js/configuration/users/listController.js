angular.module('cinnamon')
.controller('configuration.users.list', function($scope, $http, $state) {
	console.log('configuration.users.list');
	
	$scope.selectedUsers = [];
	
	$http.get('/rest/configuration/users')
	.success(function(data) {
		console.log(data);
		
		$scope.domains = data;
	});
	
	
	$scope.goView = function(user) {
		$state.go('view', {userId: user.userId});
	}
});