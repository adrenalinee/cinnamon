angular.module('cinnamon')
.controller('configuration.menus.view', function($scope, $http, $stateParams, $mdDialog) {
	console.log('configuration.menus.view');
	
	var menuId = $stateParams.menuId;
	$scope.menuId = menuId;
	
	$http.get('/rest/configuration/menus/' + menuId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
});