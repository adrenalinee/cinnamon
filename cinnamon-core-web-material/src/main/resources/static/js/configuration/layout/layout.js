angular.module('cinnamon')
.controller('layoutController', function($scope, $http, $interval, $location, $window, $mdSidenav, $mdMedia) {
	
	var dimension = $location.path().split("/")[1];
	console.log(dimension);
	
	$http.get('/rest/session/current-menus/' + dimension)
	.success(function(data) {
		console.log(data);
		$scope.currentMenus = data;
	});
	
	$scope.selectMenu = function(menu) {
		if (menu.uri != null) {
			$interval(function() {
				location.href = menu.uri;
			}, 150, 1);
		} else {
			//TODO 가능할 경우 하위 메뉴 펼침
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