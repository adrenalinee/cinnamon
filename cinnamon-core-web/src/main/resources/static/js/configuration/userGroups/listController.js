angular.module('cinnamon')
.controller('configuration.userGroups.list', function($scope, $http) {
	console.log('configuration.userGroups.list');
	
	$scope.domains;
	$scope.searchInfo = {
		sort: 'createdAt,desc'
	};
	
//	$scope.sortFields = [
//	{userGroupId: 'userGroupId'},
//	{name: '사용자 모임 이름'},
//	{createdAt: '생성일'}
//	];
	
	$scope.sortFields = {
			userGroupId: 'userGroupId',
			name: '사용자 모임 이름',
			createdAt: '생성일'
	};
	
	$scope.onChangeOrder = function(field, direction) {
		$scope.searchInfo.sort = field + ',' + direction;
		$scope.searchInfo.page = 1;
		$scope.search();
	}
	
	$scope.load = function(params) {
		$http.get('/rest/configuration/userGroups', {params: params})
		.success(function(data) {
			console.log(data);
			
			$scope.domains = data;
		});
	}
	
	$scope.onSearch = function(event) {
		if (event.keyCode == 13) {
			$scope.searchInfo.page = 1;
			$scope.search();
		}
	}
	
	$scope.search = function() {
		console.info('search');
		
		$scope.showDetailSearch = false;
		var params = angular.copy($scope.searchInfo);
		for(param in params) {
			if (param == 'page') {
				params[param]--;
			}
		}
		
//		$location.search($scope.searchInfo);
		
		$scope.load(params);
	}
	
	$scope.initSearch = function() {
		$scope.searchInfo = {
			sort: 'createdAt,desc'
		};
		$scope.showDetailSearch = false;
		
		$scope.search();
	}
	
	$scope.load($scope.searchInfo);
});