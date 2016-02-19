angular.module('cinnamon')
.controller('layoutController', function($scope, $http, $interval, $window, $mdSidenav, $mdMedia) {
	
	$http.get('/rest/session/current-menus/configuration')
	.success(function(data) {
		console.log(data);
		$scope.currentMenus = data;
	});
	
	$scope.selectMenu = function(menu) {
		if (menu.uri != null) {
			$interval(function() {
				location.href = menu.uri;
			}, 200);
		} else {
			
		}
	}
	
	$scope.toggleSidenav = function() {
		$mdSidenav('leftSidenav').toggle();
	}
	
	$scope.closeSidenav = function() {
		$mdSidenav('leftSidenav').close();
	}
	
	$scope.showMenuButton = function() {
		return !$mdMedia('gt-sm');
	}
	
	
	$scope.sidenavStyle;
	$scope.isHideSidebarToolbar;
	onResize($scope, $mdMedia);
	
	angular.element($window).on('resize', function(event) {
		console.log('resize');
		
		onResize($scope, $mdMedia);
		
		$scope.$apply();
	});
	
});

function onResize($scope, $mdMedia) {
	var greaterThenSmallSize = $mdMedia('gt-sm');
	
	$scope.isHideSidebarToolbar = greaterThenSmallSize;
	if (greaterThenSmallSize) {
		$scope.sidenavStyle = {
			background: 'transparent'
		};
	} else {
		$scope.sidenavStyle = {};
	}
}