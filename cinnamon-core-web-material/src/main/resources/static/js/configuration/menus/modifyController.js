angular.module('cinnamon')
.controller('configuration.menus.modify', function($scope, $http, $stateParams, $mdDialog, $mdMedia, message, pageMove, $state) {
	console.log('configuration.menus.modify');
	
	var menuId = $stateParams.menuId;
	
	$scope.menuId = menuId;
	
	$http.get('/rest/configuration/groups/menuTypes/childs')
	.success(function(data) {
		$scope.menuTypes = data;
	});
	
	$http.get('/rest/configuration/groups/menuPositions/childs')
	.success(function(data) {
		$scope.menuPositions = data;
	});
	
	
	
	$scope.load = function() {
			$http.get('/rest/configuration/menus/' + menuId)
			.success(function(data) {
				console.log(data);
				$scope.menu = data;
		});
	}
		
	$scope.load();

	// 정보 수정
	$scope.modify = function() {
		var params = angular.copy($scope.menu);
		$http.put('/rest/configuration/menus/' + menuId , params)
			.success(function(result) {
				message.alert('수정되었습니다.');
				pageMove.go('view', {menuId : menuId});
		})
	}
	
	// 리스트로 이동
	$scope.goBack = function() {
		pageMove.go('view' , {menuId : menuId});
	}
});