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
			defaultSorts: '=?', //'createdAt,desc' or ['createdAt,desc', 'name,asc']
			isPaging: '=?'
		},
		templateUrl: '/configuration/directives/searchList',
		controller: 'searchListController'
	}
}).controller('searchListController', function($scope, $http, $location, $mdMedia) {
	console.log('searchListController');
	
	if (!angular.isDefined($scope.searchInfo)) {
		$scope.searchInfo = {};
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
		}).error(function(error) {
			console.log('no data');
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
		
		$location.search($scope.searchInfo);
		
		$scope.load(params);
	}
	
	$scope.initSearch = function() {
		$scope.searchInfo = {};
		
		if (angular.isDefined($scope.defaultSearchParams)) {
			$scope.searchInfo = $scope.defaultSearchParams;
		}
		
		if (angular.isDefined($scope.defaultSorts)) {
			$scope.searchInfo.sort = $scope.defaultSorts;
		}
		
		$scope.showDetailSearch = false;
		$scope.load($scope.searchInfo);
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
	
	$scope.initSearch();
	$scope.load($scope.searchInfo);
});