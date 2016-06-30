angular.module('cinnamon')
.controller('layoutController', function($scope, $http, $interval, $location, $window, $mdSidenav, $mdMedia) {
	
	var dimension = $location.path().split("/")[1];
	console.log(dimension);
	
	
	$scope.selectMenu = function(uri) {
		console.log('selectMenu');
		
		console.log(uri);
		
		if (uri != null) {
			if (uri == $location.path()) {
				return;
			}
			
			$interval(function() {
				location.href = uri;
			}, 150, 1);
		}
	}
	
	$scope.toggleSidenav = function() {
		$mdSidenav('leftSidenav').toggle();
	}
	
	$scope.closeSidenav = function() {
		$mdSidenav('leftSidenav').close();
	}
	
	$scope.isMobile = function() {
		return !$mdMedia('gt-sm');
	}
	
	
}).factory('cmHeader', function($scope, $rootScope) {
	return {
		setTabs: function(tabs) {
			
		}
	}
});

//function onResize($scope, $mdMedia) {
//	var greaterThenSmallSize = $mdMedia('gt-sm');
//	
//	$scope.isHideSidebarToolbar = greaterThenSmallSize;
//	if (greaterThenSmallSize) {
//		$scope.sidenavStyle = {
//			background: 'transparent'
//		};
//	} else {
//		$scope.sidenavStyle = {};
//	}
//}