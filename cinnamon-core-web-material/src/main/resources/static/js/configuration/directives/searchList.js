angular.module('cinnamon')
.directive('cmSearchList', function() {
	return {
		restrict: 'E',
		transclude: {
			'items': 'cmSearchListItems',
			'filters': 'cmSearchListFilters',
		},
		scope: {
			domains: '=',
//			searchInfo: '=searchParams',
			resourceUrl: '=',
			defaultSearchParams: '=?',
			sortItems: '=?',
			isPaging: '=?'
		},
		templateUrl: '/configuration/directives/searchList',
		controller: 'searchListController'
	}
}).controller('searchListController', function($scope, $http) {
	console.log('searchListController');
	
//	$scope.domains;
	
	if (angular.isDefined($scope.defaultSearchParams)) {
		$scope.searchInfo = $scope.defaultSearchParams;
	}
	
	if (angular.isDefined($scope.isPaging)) {
		$scope.isPaging = $scope.isPaging;
	} else {
		$scope.isPaging = true
	}
	
	$scope.showDetailSearch = false;
	
	$scope.onSearch = function(event) {
		if (event.keyCode == 13) {
			$scope.searchInfo.page = 1;
			$scope.search();
		}
	}
	
	$scope.load = function(params) {
		console.log(params);
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