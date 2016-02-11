angular.module('cinnamon')
.controller('configuration.users.list', function($scope, $http, $interval, $state) {
	console.log('configuration.users.list');
	
	$scope.selectedUsers = [];
	
//	$http.get('/rest/configuration/users')
//	.success(function(data) {
//		console.log(data);
//		
//		$scope.domains = data;
//	});
	
	
	$scope.goView = function(user) {
		$interval(function() {
			$state.go('view', {userId: user.userId});
		}, 200);
	}
	
	
	$scope.load = function(params) {
		$http.get('/rest/configuration/users', {params: params})
		.success(function(data) {
			console.log(data);
			
			$scope.domains = data;
		});
	}
	
	
	$scope.onPageChange = function() {
		console.log('onPageChange');
		$scope.search();
	}
	
	
	$scope.search = function() {
		console.info('search');
		
		var params = angular.copy($scope.searchInfo);
		for(param in params) {
			if (param == 'page') {
				params[param]--;
			}
		}
		
//		$location.search($scope.searchInfo);
		
		$scope.load(params);
	}
	
	
	$scope.load($scope.searchInfo);
});