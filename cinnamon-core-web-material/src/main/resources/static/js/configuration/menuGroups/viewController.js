angular.module('cinnamon')
.controller('configuration.menuGroups.view', function($scope, $http, $stateParams, $mdDialog) {
	console.log('configuration.menuGroups.view');
	
	var menuGroupId = $stateParams.menuGroupId;
	$scope.menuGroupId = menuGroupId;
	
	$http.get('/rest/configuration/menuGroups/' + menuGroupId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$scope.goMenuView = function(menu) {
		location.href = '/configuration/menus/' + menu.menuId;
	}
	
});