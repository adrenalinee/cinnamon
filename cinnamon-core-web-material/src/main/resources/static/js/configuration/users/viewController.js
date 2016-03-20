angular.module('cinnamon')
.controller('configuration.users.view', function($scope, $http, $interval, $state, $stateParams, $mdDialog, $mdMedia) {
	console.log('configuration.users.view');
	
	var userId = $stateParams.userId;
	$scope.userId = userId;
	
	
	$http.get('/rest/configuration/users/' + userId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$scope.selectUserGroup = function($event) {
//		$mdDialog.show(
//			$mdDialog.alert()
//				.title('사용자 모임 선택')
//				.targetEvent($event)
//		);
		
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
	
	$scope.modify = function($event) {
		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
		$mdDialog.show({
			targetEvent: $event,
			fullscreen: useFullScreen,
			templateUrl: '/configuration/partials/users/modify2',
			controller: 'configuration.users.modify'
		});
	}
});