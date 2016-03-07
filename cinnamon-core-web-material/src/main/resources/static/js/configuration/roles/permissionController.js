/**
 * 
 */
angular.module('cinnamon')
.controller('configuration.roles.permission', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state, $mdToast){
	console.log('configuration.roles.permission');
	
	// 메뉴
	$scope.allMenus;
	// 메뉴 권한 정보
	$scope.permissionMenus;
	// 메뉴 그룹
	$scope.menuGroups;	
	// 현재 선택된 메뉴 그룹아이디
	$scope.menuGroupid;
	// 사이트 정보
	$scope.sites;
	
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
	
	$scope.site = function() {
		$log.info("site");
		$http.get('/rest/configuration/sites/' + $stateParams.siteId)
		.then ( 
			function(result) {
				$log.info(result);
				$scope.sites = result.data; 
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
		$http.get('/rest/configuration/menuGroups/site/' + siteId)
			.then(
					function(result) {
						$log.info(result);
						$scope.menuGroups = result.data;
					},
					function(error) {
						$log.error('메뉴 그룹 가져오기 error' + error);
					}
			)
	}
	
	// 메뉴 그룹 가져오기
	$scope.selectSite($stateParams.siteId);
	
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
				$scope.menuGroupId = menuGroupId;
			},
			function(error) {
				$log.error('메뉴 권한 정보를 가져올 수 없습니다. error ' + error);
			}
		)
	}
	// 메뉴 리스트 가져오기
	$scope.selectMenuGroup($stateParams.siteId, $stateParams.menuGroupId);
	
	
	// 메뉴 권한 정보 수정
	$scope.updateMenu = function() {
		var url ="/rest/configuration/roles/" + $scope.domains.permissionId + "/menus/" + $scope.menuGroupId;
		// 권한 선택 메뉴
		var permitMenus = $scope.permissionMenus;
		// 변경되는 메뉴
		var changePermitMenu = [];
		
		
		for(var obj in permitMenus){
			changePermitMenu.push({menuId : obj, permitRoot : permitMenus[obj].permitRoot}); 
		}
		console.log(changePermitMenu);

		$http.put(url, changePermitMenu).then(
			function(data) {
				$scope.showActionToast();
			},
			function(error) {
				$log.error('수정 중 에러가 발생되었습니다. error : ' + error)
			}
		);
	}	
	
	// 수정완료
	$scope.showActionToast = function() {
	    $mdToast.show(
	    	      $mdToast.simple()
	    	        .textContent('수정되었습니다')
	    	        .position('top right')
	    	        .hideDelay(3000)
	    	    );
	}
	
})
;