angular.module('cinnamon')
.controller('configuration.group.view', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia, $compile) {
	console.log('configuration.group.view');
	
	// 스크린 조절을 위한 값
	$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
	
	// 화면 도메인
	$scope.domains;
	
	var groupId;
	// 기본 정보
	$scope.load = function() {
		$http.get('/rest/configuration/groups/' + $stateParams.groupId)
			.success(function(result) {
				console.log(result);
				$scope.domains = result;
				groupId = result.groupId;
				
				$scope.loadChilds();
			})
	}
	$scope.load();
	
	// child domains
	$scope.childs;
	
	// childs load
	$scope.loadChilds = function() {
		$http.get('/rest/configuration/groups/' + $scope.domains.groupId + '/childs')
			.success(function(result) {
				console.log(result);
				$scope.childs = result;
		})
	}
	
	// 자식 등록 팝업
	$scope.popupCreateChild = function($event) {
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))&& $scope.customFullscreen;
		$mdDialog.show({
			parentGroupId: $scope.domains.groupId,
			templateUrl : '/configuration/partials/groups/createChild',
			clickOutsideToClose:true,
			parent: angular.element(document.body),
			controller : 
				function createChildController($scope, $mdDialog, $http) {
					$scope.searchInfo = {
						sort : 'parentGroupId,asc'
					};
					$scope.close = function() {
						$mdDialog.hide();
					}
					// 등록 완료
					$scope.createChild = function(group) {
						if(!$scope.frm.$valid) {
							$mdToast.show(
									$mdToast.simple()
									.textContent('입력값을 확인하세요.')
									.position('top right')
									.hideDelay(3000)
								)
							return;
						}
						var params = angular.copy(group);
						$http.post('/rest/configuration/groups/' + groupId + '/childs', params)
							.success(function(result) {
								$mdToast.show(
									$mdToast.simple()
									.textContent('등록되었습니다.')
									.position('top right')
									.hideDelay(3000)
								)
						})
						.error(function(){
							$mdToast.show(
									$mdToast.simple()
									.textContent('groupId가 중복입니다. 다시 확인해주세요.')
									.position('top right')
									.hideDelay(3000)
								)
						})
						$mdDialog.hide();
					}
				}
		})
		.then(function() {
			$scope.loadChilds();
		}, function() {
			
		})
	}

	// 부모 코드 리스트 조회 팝업
	$scope.popupParentList = function() {
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))&& $scope.customFullscreen;
		
		$mdDialog.show({
			parentGroupId: $scope.domains.groupId,
			templateUrl : '/configuration/partials/groups/parentList',
			clickOutsideToClose:true,
			parent: angular.element(document.body),
			fullscreen: useFullScreen,
			controller : 
				function controller($scope, $mdDialog, $http) {
					$scope.searchInfo = {
						sort : 'parentGroupId,asc'
					};
					
					$scope.close = function() {
						$mdDialog.hide();
					}
					
					$scope.selectParent = function(domain) {
						$http.put('/rest/configuration/groups/' + domain.groupId + "/parent", groupId)
							.success(function(result) {
								$mdToast.show(
									$mdToast.simple()
									.textContent('등록되었습니다.')
									.position('top right')
									.hideDelay(3000)
								)
						})
						$mdDialog.hide();
					}
				}
		})
		.then(function(parentId) {
			$scope.load();
		}, function() {
			
		})
		
		$scope.$watch(function() {
			return $mdMedia('xs') || $mdMedia('sm');
		}, function(wantsFullScreen) {
			$scope.customFullscreen = (wantsFullScreen === true);
		});
	}
	
	// 목록으로 이동
	$scope.goList = function() {
		$interval(function() {
			$state.go('list');
		}, 200, 1);
	}	

});