angular.module('cinnamon')
.controller('layoutController', function($scope, $http) {
	
	$http.get('/rest/session/current-menus/configuration')
	.success(function(data) {
		console.log(data);
		$scope.currentMenus = data;
	});
	
	$scope.selectMenu = function(menu) {
		if (menu.uri != null) {
			location.href = menu.uri;
		} else {
			
		}
	}
});