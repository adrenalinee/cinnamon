angular.module('cinnamon')
.controller('configuration.sites.view', function($scope, $http, $stateParams, $mdDialog, $mdMedia, $mdToast) {
	console.log('configuration.sites.view');
	
	var siteId = $stateParams.siteId;
	var siteInfo;
	$scope.searchInfo = {};
		$scope.load = function() {
			$http.get('/rest/configuration/sites/' + siteId)
			.success(function(data) {
				console.log(data);
				
				siteInfo = $scope.domain = data;
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
		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
		$mdDialog.show({
			targetEvent: $event,
			fullscreen: useFullScreen,
			templateUrl : '/configuration/partials/menuGroups/select',
			controller : function defaultMenuGroupController($scope, $mdDialog, $http, $mdToast) {
					$scope.siteInfo = siteInfo;
					$scope.searchInfo = {};
				
					$scope.domains;
					// 메뉴 그룹 불러오기
					$scope.load = function() {
						$http.get("/rest/configuration/menuGroups")
							.success(function(result) {
								console.log(result);
								$scope.domains = result;
							})
					}
					$scope.load();
					
					// 기본 메뉴 설정
					$scope.putDefaultMenuGroup = function(menuGroupId) {
						$http.put('/rest/configuration/sites/' + siteInfo.siteId + "/defaultMenuGroup/" + menuGroupId)
							.success(function(result) {
								$mdToast.show(
									$mdToast.simple()
									.textContent('등록되었습니다')
									.position('top right')
									.hideDelay(3000)
								)
								$mdDialog.hide();
							})
					}
		
					$scope.$watch(function() {
						return $mdMedia('xs') || $mdMedia('sm');
					}, function(wantsFullScreen) {
						$scope.customFullscreen = (wantsFullScreen === true);
					});
			}
		}).then(function() {
			$scope.load();
		})
	}
});