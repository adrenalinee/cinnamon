angular.module('cinnamon')
.controller('configuration.menus.modify', function($scope, $http, $stateParams, $mdDialog, $mdMedia) {
	console.log('configuration.menus.modify');
	
	
	var menuId = $stateParams.menuId;
	$scope.menuId = menuId;
	
	$scope.load = function() {
			$http.get('/rest/configuration/menus/' + menuId)
			.success(function(data) {
				console.log(data);
				$scope.menu = data;
		});
	}
		
	$scope.load();
});