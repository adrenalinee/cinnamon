angular.module('cinnamon')
.controller('configuration.menus.view', function($scope, $http, $stateParams, $mdDialog, $mdMedia, message, pageMove) {
	console.log('configuration.menus.view');
	
	var menuId = $stateParams.menuId;
	$scope.menuId = menuId;
	var menuGroupId;
		$scope.load = function() {
			$http.get('/rest/configuration/menus/' + menuId)
			.success(function(data) {
				console.log(data);
				
				$scope.domain = data;
				menuGroupId = data.menuGroup.menuGroupId;
		});
	}

	$scope.load();
	
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
				console.log(isDeleteable);
				
				if (isDeleteable) {
					$http.delete('/rest/configuration/menus/' + menuId)
						.success(function(data) {
							message.alert('삭제되었습니다.');
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
	
	$scope.menus;
	// 메뉴 가져오기
	$scope.getMenu = function() {
		$scope.searchInfo = {parentMenuId: menuId};
		var params = angular.copy($scope.searchInfo);
		console.log(params);
		$http.get('/rest/configuration/menus', {params : params})
			.success(function(result) {
				$scope.menus = result;
			})
		
	}
	
	// 메뉴 작성
	$scope.openCreateMenu = function() {
		
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))&& $scope.customFullscreen;
		
		$mdDialog.show({
			templateUrl : '/configuration/partials/menus/create',
			parent : angular.element(document.body),
			fullscreen: useFullScreen,
			clickOutsideToClose:true,
			fullscreen : useFullScreen,
			controller : 
				function controller($scope, $mdDialog, $http, $mdToast) {
					$scope.menu = {
							menuType : 'nomal',
							position : 'sidebar',
							parentMenuId :''
					}
					// 메뉴 종류
					$scope.menuTypes = [{ code : 'normal' },{code : 'separater'} , {code : 'label'} , {code : 'component'}]
					// 메뉴 위치
					$scope.menuPositions = [{code : 'sidebar'} , {code : 'headerRight'} , {code : 'headerLeft'}]
				
					$scope.close = function() {
						$mdDialog.hide(false);
					}
					
					// 메뉴 생성
					$scope.create = function() {
						if ($scope.frm.$valid == false) {
							$mdToast.show(
								$mdToast.simple()
								.position('top right')
								.textContent('입력값을 확인하시기 바랍니다.'));
							return;
						}
						
						$scope.menu.menuGroupId = menuGroupId;
						$scope.menu.parentMenuId = menuId;
						var params = angular.copy($scope.menu);
						console.log(params);
						
						$http.post('/rest/configuration/menus', params)
							.success(function(result, status, headers) {
								$mdToast.show(
									$mdToast.simple()
										.textContent('등록되었습니다.')
										.position('right top')
										.hideDelay(3000)
								)
								$mdDialog.hide(true);
						})
					}
				}
		}).then(function(result) {
			if(result) {
				$scope.getMenu();
			}
		})
		
		$scope.$watch(function() {
			return $mdMedia('xs') || $mdMedia('sm');
		}, function(wantsFullScreen) {
			$scope.customFullscreen = (wantsFullScreen === true);
		});
	}

});