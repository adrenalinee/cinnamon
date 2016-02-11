angular.module('cinnamon')
.controller('configuration.userGroups.list', function($scope, $http, $interval, $state) {
	console.log('configuration.userGroups.list');
	
	$scope.domains;
	
//	$http.get('/rest/configuration/userGroups')
//	.success(function(data) {
//		console.log(data);
//		
//		$scope.domains = data;
//	});
	
	$scope.goView = function(userGroup) {
		$interval(function() {
			$state.go('view', {userGroupId: userGroup.userGroupId});
		}, 200);
	}
});