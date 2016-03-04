angular.module('cinnamon')
.controller('configuration.roles.view', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia) {
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
	// 메뉴
	$scope.allMenus;
	// 메뉴 권한 정보
	$scope.permissionMenus;
	
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
	
	// 메뉴 그룹 가져오기
	$scope.selectSite = function(siteId) {
		$http.get('/rest/configuration/menuGroups',{siteId : siteId})
			.then(
					function(result) {
						$log.info(result);
						$scope.menuGroups = result.data.content;
					},
					function(error) {
						$log.error('메뉴 그룹 가져오기 error' + error);
					}
			)
	}
	
	// 메뉴 리스트 가져오기
	$scope.selectMenuGroup = function(siteId, menuGroupId) {
		console.log("selectMenuGroup");
		var url = "/rest/configuration/sites/" + siteId + "/menuGroups/" + menuGroupId + "/menus";
		$http.get(url)
		.then(
			function(result) {
				console.info(result);
				$scope.allMenus = result.data;
				$scope.getPermissions(menuGroupId);
			},
			function (error) {
				$log.error('전체 메뉴 가져오기 error' + error);
			}
		)
	}
	
	// 메뉴 권한 정보 가져오기
	$scope.getPermissions = function(menuGroupId) {
		url = "/rest/configuration/roles/" + $scope.domains.permissionId + "/menus?menuGroupId=" + menuGroupId;
		$http.get(url)
		.then(
			function(result) {
				$log.info(result);
				$scope.permissionMenus = result.data;
			},
			function(error) {
				$log.error('메뉴 권한 정보를 가져올 수 없습니다. error ' + error);
			}
		)
	}

	// 아코디언 메뉴
	$scope.oneAtATime = true;
	
  $scope.groups = [
                   {
                     title: 'Dynamic Group Header - 1',
                     content: 'Dynamic Group Body - 1'
                   },
                   {
                     title: 'Dynamic Group Header - 2',
                     content: 'Dynamic Group Body - 2'
                   }
                 ];	
  $scope.status = {
		    isFirstOpen: true,
		    isFirstDisabled: false
		  };
});