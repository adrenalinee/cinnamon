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
			resourceUrl: '@',
			searchInfo: '=?',
			defaultSearchParams: '=?',
			sortItems: '=?',
			defaultSorts: '=?', //ex: 'createdAt,desc' or ['createdAt,desc', 'name,asc']
			isPaging: '=?'
		},
		templateUrl: '/configuration/directives/searchList',
		controller: 'searchListController'
	}
}).controller('searchListController', function($scope, $http, $location, $mdMedia, $log) {
	console.log('searchListController');
	
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
	
	$scope.load = function() {
		var params = angular.copy($scope.searchInfo);
		if (params.page != undefined) {
			params.page--;
		}
		$log.info(params);
		$scope.showProgress = true;
		$http.get($scope.resourceUrl, {params: params})
		.success(function(data) {
			$scope.domains = data;
		}).finally(function() {
			$scope.showProgress = false;
		});
	}
	
	$scope.onPageChange = function() {
		
		$scope.searchInfo.page = $scope.current.page;
		$scope.search();
	}
	
	$scope.search = function() {
		$location.search($scope.searchInfo);
		$scope.load();
	}
	
	$scope.clearSearch = function() {
		$scope.current = {};
		if (angular.isDefined($scope.defaultSearchParams)) {
			$scope.searchInfo = $scope.defaultSearchParams;
		} else {
			$scope.searchInfo = {};
		}
		
		if (angular.isDefined($scope.defaultSorts)) {
			$scope.searchInfo.sort = $scope.defaultSorts;
			
			var defaultSortInfo = $scope.defaultSorts.split(',');
			
			$scope.sort.key = defaultSortInfo[0];
			$scope.sort.direction = defaultSortInfo[1];
		}
		
		$scope.showDetailSearch = false;
		$location.search({});
		
		$scope.load();
	}
	
	
	$scope.isMobile = function() {
		return !$mdMedia('gt-sm');
	}
	
	
	$scope.sortItem = function(key ,value) {
		console.info('sortItem');
		// 같으면 내림차순
		if($scope.sort.key == key) {
			$scope.sort.direction == 'desc' ? $scope.sort.direction = 'asc' : $scope.sort.direction = 'desc';
		}else{
		// 다르면 오름차순
			$scope.sort.key = key;
			$scope.sort.direction = 'asc' ? $scope.sort.direction = 'desc' : $scope.sort.direction = 'asc';
		}
		// 정렬값 셋팅
		$scope.searchInfo.sort = $scope.sort.key + "," + $scope.sort.direction;
		$scope.search();
	}
	
	
	$scope.init = function() {
		$scope.sort = {};
		
		var queryString = $location.search();
		
		if (Object.keys(queryString).length <= 0) {
			$scope.current = {};
			if (angular.isDefined($scope.defaultSearchParams)) {
				$scope.searchInfo = $scope.defaultSearchParams;
			} else {
				$scope.searchInfo = {};
			}
			
			if (angular.isDefined($scope.defaultSorts)) {
				$scope.searchInfo.sort = $scope.defaultSorts;
				
				var defaultSortInfo = $scope.defaultSorts.split(',');
				
				$scope.sort.key = defaultSortInfo[0];
				$scope.sort.direction = defaultSortInfo[1];
			}
		} else {
			$scope.searchInfo = queryString;
			if (queryString.sort != undefined) {
				var sortInfo = queryString.sort.split(',');
				
				$scope.sort.key = sortInfo[0];
				$scope.sort.direction = sortInfo[1];
			}
			
			if ($scope.searchInfo.page != undefined) {
				$scope.current = {
					page: Number($scope.searchInfo.page)
				};
			}
		}
	}
	
	$scope.init();
	$scope.load();
});