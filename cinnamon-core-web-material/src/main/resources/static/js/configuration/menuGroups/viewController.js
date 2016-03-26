angular.module('cinnamon')
.controller('configuration.menuGroups.view', function($scope, $http, $stateParams, $mdDialog, $mdMedia, $interval) {
	console.log('configuration.menuGroups.view');
	
	var menuGroupId = $stateParams.menuGroupId;
	$scope.menuGroupId = menuGroupId;
	
	$scope.load = function() {
		$http.get('/rest/configuration/menuGroups/' + menuGroupId)
		.success(function(data) {
			console.log(data);
			
			$scope.domain = data;
		});
	}
	
	$scope.load();
	
	$scope.goMenuView = function(menu) {
		location.href = '/configuration/menus/' + menu.menuId;
	}
	
	$scope.sites;
	var sites;
	// 메뉴그룹 별 사이트 목록 가져오기
	$scope.sitesOfMenuGroup = function() {
		$http.get('/rest/configuration/menuGroups/' + menuGroupId + '/sites')
		 .success(function(data) {
			 console.log(data);
			 sites = $scope.sites = data;
		 })
	}
	
	$scope.sitesOfMenuGroup();
	
	// 사이트 리스트 열기
	$scope.openSiteList = function() {
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))&& $scope.customFullscreen;
		
		$mdDialog.show({
			templateUrl : '/configuration/partials/sites/selectList',
			parent : angular.element(document.body),
			fullscreen: useFullScreen,
			clickOutsideToClose:true,
			fullscreen : useFullScreen,
			controller : 
				function controller($scope, $mdDialog, $http, $mdToast) {
					$scope.siteId = sites.siteId;
					console.log($scope.sites);

					// 선택된 메뉴 그룹 체크
					$scope.checkMenuGroup = function(siteId) {
						if(siteId == undefined) {
							return '미설정';
						}
					}
					
					// 메뉴 그룹의 사이트 정보 추가
					$scope.putSiteOfMenuGroup = function(siteId) {
						$http.put('/rest/configuration/menuGroups/' + menuGroupId + '/site/' + siteId)
							.success(function(result) {
								console.log("사이트 정보 추가 됨");
								$mdToast.show(
									$mdToast.simple()
										.textContent("등록되었습니다.")
										.position("top right")
										.hideDelay(3000)
								)
								
								$mdDialog.hide();
							})
					}
					$scope.close = function() {
						$mdDialog.hide();
					}
				}
		
		}).then(function() {
			$scope.sitesOfMenuGroup();
		})
		
		$scope.$watch(function() {
			return $mdMedia('xs') || $mdMedia('sm');
		}, function(wantsFullScreen) {
			$scope.customFullscreen = (wantsFullScreen === true);
		});
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
							position : 'sidebar'
					}
					// 메뉴 종류
					$scope.menuTypes = [{ code : 'nomal' },{code : 'separater'} , {code : 'label'} , {code : 'component'}]
					// 메뉴 위치
					$scope.menuPositions = [{code : 'sidebar'} , {code : 'headerRight'} , {code : 'headerLeft'}]
				
					$scope.close = function() {
						$mdDialog.hide();
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
								
								$mdDialog.hide();
							})
					}
				}
		}).then(function() {
			$scope.getMenus();
		})
		
		$scope.$watch(function() {
			return $mdMedia('xs') || $mdMedia('sm');
		}, function(wantsFullScreen) {
			$scope.customFullscreen = (wantsFullScreen === true);
		});
	}
	
	$scope.sidebarMenus;
	$scope.headerLeftMenus;
	$scope.headerRightMenus;
	$scope.getMenus = function () {
		
		// 각 메뉴 탭 데이터 갱신
		$http.get('/rest/configuration/menus', {params: {menuGroupId : menuGroupId, position: 'sidebar', page:0}})
			.success(function(result) {
				console.log(result);
				$scope.sidebarMenus = result;
		})
			
		$http.get('/rest/configuration/menus', {params: {menuGroupId : menuGroupId, position: 'headerLeft', page:0}})
			.success(function(result) {
				console.log(result);
				$scope.headerLeftMenus = result;
		})
			
		$http.get('/rest/configuration/menus', {params: {menuGroupId : menuGroupId, position: 'headerRight', page:0}})
			.success(function(result) {
				console.log(result);
				$scope.headerRightMenus = result;
		})
	}

});