angular.module('cinnamon')
.controller('configuration.roles.view', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state) {
	console.log('configuration.roles.view');
	
	// 초기 리스트 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/roles/' + $stateParams.permissionId)
		.then(
			function(result) {
				$scope.domains = result.data;
			}
			,function(error) {
				$log.error('error');
			}
		)
	}
	
	// 권한 정보 가져오기
	$scope.load();
	// 사이트
	$scope.sites;
	// 메뉴 그룹
	$scope.menuGroups;
	
	$scope.site = function() {
		$http.get('/rest/configuration/sites')
		.then ( 
			function(result) {
				$log.info(result);
				$scope.sites = result.data.content; 
			},
			function(error) {
				$log.error('사이트 가져오기 error' + error);
			}
		)
	}
	// 사이트 가져오기
	$scope.site();
	$scope.stieId;
	// 메뉴 그룹 가져오기
	$scope.selectSite = function(siteId) {
		$http.get('/rest/configuration/menuGroups/site/' + siteId)
			.then(
					function(result) {
						$log.info(result);
						$scope.menuGroups = result.data;
						$scope.siteId = siteId;
					},
					function(error) {
						$log.error('메뉴 그룹 가져오기 error' + error);
					}
			)
	}
	
	$scope.goPermission = function(menuGroupId) {
		$interval(function() {
			$state.go('permissionView', {permissionId : $stateParams.permissionId, siteId : $scope.siteId, menuGroupId : menuGroupId});
		}, 200, 1);
	}
});
