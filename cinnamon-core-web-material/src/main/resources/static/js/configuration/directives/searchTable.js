angular.module('cinnamon')
.directive('cmSearchTable', function() {
	return {
		restrict: 'E',
		transclude: {
			'items': 'cmSearchTableItems',
			'filters': 'cmSearchTableFilters',
		},
		scope: {
			domains: '=',
//			searchInfo: '=searchParams',
			resourceUrl: '@',
			defaultSearchParams: '=?',
			sortItems: '=?',
			isPaging: '=?',
			searchInfo: '=?'
		},
		templateUrl: '/configuration/directives/searchTable',
		controller: 'searchTableController'
	}
}).controller('searchTableController', function($scope, $http, $location, $mdMedia) {
	console.log('searchTableController');
	
//	$scope.domains;
	
	console.log($scope.defaultSearchParams)
	
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
		})
		.error(function(error) {
			console.log('no data');
		})
		.finally(function() {
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
		
		$location.search($scope.searchInfo);
		
		$scope.load(params);
	}
	
	$scope.isMobile = function() {
		return !$mdMedia('gt-sm');
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