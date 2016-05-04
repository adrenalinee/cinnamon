angular.module('cinnamon')
.controller('layoutController', function($scope, $http, $interval, $location, $window, $mdSidenav, $mdMedia) {
	
	var dimension = $location.path().split("/")[1];
	console.log(dimension);
	
	$http.get('/rest/cinnamon/session/current-menus/' + dimension)
	.success(function(data) {
		console.log(data);
		$scope.currentMenus = data;
		
		var nowPath = $location.path();
		
		$scope.selectedMenu;
		$scope.selectedChildMenuIndex = -1;
		
		
		for (var i = 0; i < data.sidebar.length; i++) {
			var menu = data.sidebar[i];
			$scope.selectedMenu = menu;
			
			if (nowPath.startsWith(menu.uri)) {
				$scope.selectedMenuIndex = i;
				
				if (menu.childs == undefined) {
					continue;
				}
				
				if (menu.childs.length <= 0) {
					continue;
				}
				
				$scope.childMenus = menu.childs;
				for (var j = 0; j < menu.childs.length; j++) {
					var childMenu = menu.childs[j];
					
					if (nowPath.startsWith(childMenu.uri)) {
						$scope.selectedChildMenuIndex = j;
						
						break;
					}
				}
				break;
			}
		}
		
		
		
//		var index = 0;
//		data.sidebar.forEach(function(value) {
//			if (nowPath.startsWith(value.uri)) {
//				$scope.selectedMenuIndex = index;
//				
//				//2depth 메뉴 셋팅
//				if (value.childs != undefined) {
//					if (value.childs.length > 0) {
//						value.childs.forEach(function(child) {
//							if (child.)
//						});
//						$scope.childMenus = value.childs;
//					}
//				}
//			}
//			index++;
//		});
		
	});
	
	$http.get('/rest/cinnamon/session/me')
	.success(function(data) {
		console.log(data);
		$scope.me = data;
	});
	
	
	$scope.selectChildMenu = function(menu) {
		console.log('selectChildMenu');
		console.log(menu);
		
		if (menu.uri != null) {
			var nowPath = $location.path();
			console.log(nowPath);
			console.log(menu.uri);
			if (nowPath.startsWith(menu.uri)) {
				return;
			}
			
			$interval(function() {
				location.href = menu.uri;
			}, 150, 1);
		}
	}
	
	$scope.selectMenu = function(menu) {
		if (menu.uri != null) {
			$interval(function() {
				location.href = menu.uri;
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
	
	
//	$scope.sidenavStyle;
//	$scope.isHideSidebarToolbar;
//	onResize($scope, $mdMedia);
//	
//	angular.element($window).on('resize', function(event) {
//		console.log('resize');
//		
//		onResize($scope, $mdMedia);
//		
//		$scope.$apply();
//	});
	
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