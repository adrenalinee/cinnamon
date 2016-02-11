angular.module('cinnamon')
.directive('cmSearchList', function() {
	return {
		restrict: 'E',
		transclude: true,
		scope: {
			domains: '=',
			resourceUrl: '=',
			searchParams: '='
		},
		templateUrl: '/configuration/directives/searchList',
		controller: 'searchListController'
	}
}).controller('searchListController', function($scope, $http) {
	console.log('searchListController');
	
//	$scope.domains;
	$scope.searchInfo = {
		sort: 'createdAt,desc'
	};
	
	$scope.showDetailSearch;
	
	$scope.onSearch = function(event) {
		if (event.keyCode == 13) {
			$scope.searchInfo.page = 1;
			$scope.search();
		}
	}
	
	$scope.load = function(params) {
		$http.get($scope.resourceUrl, {params: params})
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