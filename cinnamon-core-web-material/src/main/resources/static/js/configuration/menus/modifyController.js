angular.module('cinnamon')
.controller('configuration.menus.modify', function($scope, $http, $stateParams, $mdDialog, $mdMedia, message, pageMove, $state) {
	console.log('configuration.menus.modify');
	
	var menuId = $stateParams.menuId;
	
	$scope.menuId = menuId;
	
	// 메뉴 종류
	$scope.menuTypes = [{ code : 'normal' },{code : 'separater'} , {code : 'label'} , {code : 'component'}]
	// 메뉴 위치
	$scope.menuPositions = [{code : 'sidebar'} , {code : 'headerRight'} , {code : 'headerLeft'}]
	
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