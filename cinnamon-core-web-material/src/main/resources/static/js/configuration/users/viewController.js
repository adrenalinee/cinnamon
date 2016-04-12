angular.module('cinnamon')
.controller('configuration.users.view', function($scope, $http, $interval, $state, $stateParams, $mdDialog, $mdMedia, groupService) {
	console.log('configuration.users.view');
	
	var userId = $stateParams.userId;
	$scope.userId = userId;
	
	$scope.nationMap;
	groupService.getGroupMap('nations',function(result) {
		$scope.nationMap = result;
		// console.log($scope.machineModelMap);
	});
	
	$http.get('/rest/configuration/users/' + userId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$scope.selectUserGroup = function($event) {
		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
		$mdDialog.show({
			targetEvent: $event,
			fullscreen: useFullScreen,
			templateUrl: '/configuration/partials/userGroups/select',
			controller: function($scope, $http, $mdDialog, $mdToast) {
				$scope.onSelect = function(userGroup) {
					console.log('onSelect');
					
					$http.put('/rest/configuration/users/' + userId + '/userGroups', {
						userGroupId: userGroup.userGroupId
					}).success(function(data) {
						$mdToast.show(
								$mdToast.simple()
								.position('top right')
								.textContent('등록되었습니다.'));
						
						//TODO 사용자 목록을 새로 불러와야 함 
					});
					
					$mdDialog.hide();
				}
			}
		});
	}
	
//	$scope.modify = function($event) {
//		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
//		$mdDialog.show({
//			targetEvent: $event,
//			fullscreen: useFullScreen,
//			templateUrl: '/configuration/partials/users/modify2',
//			controller: 'configuration.users.modify'
//		});
//	}
	
	$scope.delete = function($event) {
		$mdDialog.show(
				$mdDialog.confirm()
				.targetEvent($event)
				.title('삭제확인')
				.textContent('사용자를 삭제 하시겠습니까? 활동기록이 없는 사용자만 삭제 할 수 있습니다.')
				.ok('삭제')
				.cancel('취소')
			).then(function() {
				//지울 수 있는지 확인
				$http.get('/rest/configuration/users/' + userId + '/deleteable')
				.success(function(isDeleteable) {
					if (isDeleteable) {
						$http.delete('/rest/configuration/userId/' + userId)
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
								.textContent('활동기록이 있는 사용자는 삭제 할 수 없습니다. 정식으로 탈퇴 처리를 하면 30일이후에 활동기록이 모두 지워집니다.')
								.ok('확인'));
					}
				});
			});
	}
});