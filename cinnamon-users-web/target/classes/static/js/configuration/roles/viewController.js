angular.module('cinnamon')
.controller('configuration.roles.view', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state, $sce, pageMove) {
	console.log('configuration.roles.view');
	
	var permissionId = $stateParams.permissionId;
	$scope.permissionId = permissionId;
	
	// role 정보 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/roles/' + permissionId)
		.success(function(result) {
			$scope.domain = result;
		})
	}
	
	// role 정보 가져오기
	$scope.load();
	// 사이트
	$scope.sites;
	// 메뉴 그룹
	$scope.menuGroups;
	
	$scope.site = function() {
		$http.get('/rest/configuration/sites')
		.success(function(result) {
			$log.info(result);
			$scope.sites = result.content; 
		})
	}
	// 사이트 가져오기
	$scope.site();
	$scope.stieId;
	
	// 메뉴 그룹 가져오기
	$scope.selectSite = function(siteId) {
		$http.get('/rest/configuration/menuGroups/site/' + siteId)
			.success(function(result) {
				$log.info(result);
				$scope.menuGroups = result;
				$scope.siteId = siteId;
			})
	}
	
	$scope.selectDefaultMenu = function($event) {
		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
		$mdDialog.show({
			targetEvent: $event,
			fullscreen: useFullScreen,
			clickOutsideToClose: true,
			templateUrl: '/configuration/partials/menus/select',
			locals: {
				defaultSearchParams: {
					authority: $scope.domain.authority
				}
			},
			controller: function($scope, $http, $mdDialog, $mdToast, defaultSearchParams) {
				$scope.defaultSearchParams = defaultSearchParams;
				
				$scope.onSelect = function(menu) {
					console.log('onSelect');
					
					$http.put('/rest/configuration/roles/' + permissionId + '/defaultMenu', {
						menuId: menu.menuId
					}).success(function(data) {
						$mdToast.show(
							$mdToast.simple()
								.position('top right')
								.textContent('변경되었습니다.'));
						
						//TODO 사용자 목록을 새로 불러와야 함 
					});
					
					$mdDialog.hide();
				}
				
//				$scope.close = function() {
//					$mdDialog.hide();
//				}
			}
		}).then(function() {
			$scope.load();
		});
	}
	
	
	// 메뉴권한 페이지 이동
	$scope.goPermission = function(menuGroupId) {
		pageMove.go('permissionView', {permissionId : $stateParams.permissionId, siteId : $scope.siteId, menuGroupId : menuGroupId});
	}
	
	$scope.goList = function() {
		pageMove.go('list');
	}
});
