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
			.success(function(result) {
				$scope.domains = result;
			})
	}
	
	// 권한 정보 가져오기
	$scope.load();	
	
	$scope.site = function() {
		$http.get('/rest/configuration/sites/' + $stateParams.siteId)
			.success(function(result) {
				$log.info(result);
				$scope.sites = result; 
			})
	}
	
	// 사이트 가져오기
	$scope.site();	
	
	// 메뉴 그룹 가져오기
	$scope.selectSite = function(siteId) {
		$http.get('/rest/configuration/menuGroups/site/' + siteId)
			.success(function(result) {
				$log.info(result);
				$scope.menuGroups = result;
			})
	}
	
	// 메뉴 그룹 가져오기
	$scope.selectSite($stateParams.siteId);
	
	// 메뉴 리스트 가져오기
	$scope.selectMenuGroup = function(siteId, menuGroupId) {
		console.log("selectMenuGroup");
		
		$http.get("/rest/configuration/sites/" + siteId + "/menuGroups/" + menuGroupId + "/menus")
			.success(function(result) {
				console.info(result);
				$scope.allMenus = result;
				$scope.getPermissions(menuGroupId);
			})
	}
	
	// 메뉴 권한 정보 가져오기
	$scope.getPermissions = function(menuGroupId) {
		$http.get("/rest/configuration/roles/" + $scope.domains.permissionId + "/menus?menuGroupId=" + menuGroupId)
			.success(function(result) {
				$log.info(result);
				$scope.permissionMenus = result;
				$scope.menuGroupId = menuGroupId;
			})
	}
	
	// 메뉴 리스트 가져오기
	$scope.selectMenuGroup($stateParams.siteId, $stateParams.menuGroupId);
	
	// 메뉴 권한 정보 수정
	$scope.updateMenu = function() {
		// 권한 선택 메뉴
		var permitMenus = $scope.permissionMenus;
		// 변경되는 메뉴
		var changePermitMenu = [];
		
		for(var obj in permitMenus){
			changePermitMenu.push({menuId : obj, permitRoot : permitMenus[obj].permitRoot}); 
		}
		console.log(changePermitMenu);

		$http.put("/rest/configuration/roles/" + $scope.domains.permissionId + "/menus/" + $scope.menuGroupId, changePermitMenu)
			.success(function(data) {
				$scope.showActionToast();
		})
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