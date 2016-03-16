angular.module('cinnamon')
.controller('configuration.roles.view', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state, $sce) {
	console.log('configuration.roles.view');
	
	// role 정보 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/roles/' + $stateParams.permissionId)
		.success(function(result) {
			$scope.domains = result;
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
	
	// 메뉴권한 페이지 이동
	$scope.goPermission = function(menuGroupId) {
		$interval(function() {
			$state.go('permissionView', {permissionId : $stateParams.permissionId, siteId : $scope.siteId, menuGroupId : menuGroupId});
		}, 200, 1);
	}
});
