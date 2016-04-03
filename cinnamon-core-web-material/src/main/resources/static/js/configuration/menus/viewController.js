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
	
	
	$scope.delete = function($event) {
		$mdDialog.show(
			$mdDialog.confirm()
			.targetEvent($event)
			.title('삭제확인')
			.textContent('메뉴를 삭제 하시겠습니까?')
			.ok('삭제')
			.cancel('취소')
		).then(function() {
			//지울 수 있는지 확인
			$http.get('/rest/configuration/menus/' + menuId + '/deleteable')
			.success(function(isDeleteable) {
				if (isDeleteable) {
					$http.delete('/rest/configuration/menus/' + menuId)
						.success(function(data) {
							$mdToast.show(
								$mdToast.simple()
									.textContent('삭제되었습니다'));
							
							$state.go('list', {}, {location: 'replace'});
						});
				} else {
					$mdDialog.show(
						$mdDialog.alert()
							.targetEvent($event)
							.textContent('등록된 하위 메뉴를 모두 지운 후에 삭제 할 수 있습니다.')
							.ok('확인'));
				}
			});
		});
	}
});