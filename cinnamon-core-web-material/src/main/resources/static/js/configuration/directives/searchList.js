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
			isPaging: '=?',
			searchInfo: '='
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
		
		$scope.showProgress = true;
		$http.get($scope.resourceUrl, {params: params})
		.success(function(data) {
			console.log(data);
			
			$scope.domains = data;
		}).finally(function() {
			$scope.showProgress = false;
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
	
	$scope.sortItem = function(key ,value) {
		console.info('sortItem');
		// 같으면 내림차순
		if($scope.searchInfo.sortKey == key) {
			$scope.searchInfo.direction == 'desc' ? $scope.searchInfo.direction = 'asc' : $scope.searchInfo.direction = 'desc';
		}else{
		// 다르면 오름차순
			$scope.searchInfo.sortKey = key;
			$scope.searchInfo.direction = 'asc' ? $scope.searchInfo.direction = 'desc' : $scope.searchInfo.direction = 'asc';
		}
		// 정렬값 셋팅
		$scope.searchInfo.sort = $scope.searchInfo.sortKey + "," + $scope.searchInfo.direction;
		$scope.search();
	}
	
	$scope.load($scope.searchInfo);
});