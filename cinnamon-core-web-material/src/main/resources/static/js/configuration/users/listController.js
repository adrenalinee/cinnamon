angular.module('cinnamon')
.controller('configuration.users.list', function($scope, $http, $interval, $state, $location, $mdDialog, $mdMedia) {
	console.log('configuration.users.list');
	
//	$http.get('/rest/configuration/users')
//	.success(function(data) {
//		console.log(data);
//		
//		$scope.domains = data;
//	});
	
	
//	$scope.create = function($event) {
//		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
//		$mdDialog.show({
//			targetEvent: $event,
//			fullscreen: useFullScreen,
//			templateUrl: '/configuration/partials/users/create',
//			controller: 'configuration.users.create'
//		});
//	}
	
	
	
	
	
	$scope.searchInfo = {};
	
	$scope.onSearch = function(event) {
		if (event.keyCode == 13) {
			$scope.searchInfo.page = 1;
			$scope.search();
		}
	}
	
	
	$scope.goView = function(user) {
		$interval(function() {
			$state.go('view', {userId: user.userId});
		}, 150, 1);
	}
	
	
	$scope.load = function(params) {
		console.log(params);
		
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