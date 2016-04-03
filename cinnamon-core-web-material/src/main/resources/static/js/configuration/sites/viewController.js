angular.module('cinnamon')
.controller('configuration.sites.view', function($scope, $http, $stateParams, $state, $mdDialog, $mdMedia, $mdToast) {
	console.log('configuration.sites.view');
	
	var siteId = $stateParams.siteId;
	$scope.siteId = siteId;
	$scope.searchInfo = {};
	
	$scope.load = function() {
		$http.get('/rest/configuration/sites/' + siteId)
		.success(function(data) {
			console.log(data);
			
			$scope.domain = data;
			
			$http.get('/rest/configuration/sites/' + siteId + '/defaultMenuGroup')
			.success(function(data) {
				console.log(data);
				
				$scope.domain.defaultMenuGroup = data;
			});
		});
	}

	// 사이트 정보 불러오기
	$scope.load();

	$scope.goMenuGroupView = function(domain) {
		location.href = '/configuration/menuGroups/' + domain.menuGroupId;
	}
	
	$scope.goCreateMenuGroup = function() {
		location.href = '/configuration/menuGroups/create?siteId=' + siteId;
	}
	
	// 기본 메뉴 등록
	$scope.popupDefaultSite = function($event) {
		var useFullScreen = $mdMedia('xs');
		$mdDialog.show({
			targetEvent: $event,
			fullscreen: useFullScreen,
			templateUrl : '/configuration/partials/menuGroups/select',
//			bindToController: true,
			locals: {
				defaultSearchParams: {
					siteId: siteId
				}
			},
			controller : function defaultMenuGroupController($scope, $mdDialog, $http, $mdToast, defaultSearchParams) {
					$scope.defaultSearchParams = defaultSearchParams;
					$scope.searchInfo = {};
					
					$scope.menuGroups;
					// 메뉴 그룹 불러오기
//					$scope.load = function() {
//						$http.get("/rest/configuration/menuGroups")
//							.success(function(result) {
//								console.log(result);
//								$scope.domains = result;
//							})
//					}
//					$scope.load();
					
					
					
					
					
					// 기본 메뉴 설정
					$scope.onSelect = function(menuGroup) {
						console.log(menuGroup);
						
						$http.put('/rest/configuration/sites/' + siteId + "/defaultMenuGroup", {
							menuGroupId: menuGroup.menuGroupId
						}).success(function(data) {
								$mdToast.show(
									$mdToast.simple()
									.textContent('등록되었습니다')
									.position('top right')
									.hideDelay(3000)
								)
								$mdDialog.hide(menuGroup);
							});
					}
					
//					$scope.$watch(function() {
//						return $mdMedia('xs') || $mdMedia('sm');
//					}, function(wantsFullScreen) {
//						$scope.customFullscreen = (wantsFullScreen === true);
//					});
			}
		}).then(function(menuGroup) {
			if (menuGroup != null) {
				$scope.domain.defaultMenuGroup = menuGroup;
			}
//			$scope.load();
		});
	}
	
	
	$scope.delete = function($event) {
		$mdDialog.show(
			$mdDialog.confirm()
			.targetEvent($event)
			.title('삭제확인')
			.textContent('사이트를 삭제 하시겠습니까?')
			.ok('삭제')
			.cancel('취소')
		).then(function() {
			//지울 수 있는지 확인
			$http.get('/rest/configuration/sites/' + siteId + '/deleteable')
				.success(function(data) {
					console.log(data);
					if (data) {
						$http.delete('/rest/configuration/sites/' + siteId)
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
								.textContent('사이트에 메뉴모음이 추가되어 있어서 지울 수 없습니다.')
								.ok('확인'));
					}
				});
		});
	}
});