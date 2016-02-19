angular.module('cinnamon')
.controller('configuration.users.view', function($scope, $http, $interval, $stateParams, $mdDialog) {
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
		
		console.log($event);
		
		$mdDialog.show({
			targetEvent: $event,
			templateUrl: '/configuration/partials/userGroups/select',
			controller: function($scope, $http, $mdDialog, $mdToast) {
				$scope.close = function() {
					$mdDialog.hide();
				}
				
				$scope.onSelect = function(userGroup) {
					console.log('onSelect');
					
					$http.put('/rest/configuration/users/' + userId + '/userGroups', {
						userGroupId: userGroup.userGroupId
					}).success(function(data) {
						$mdToast.show(
								$mdToast.simple()
								.position('top right')
								.textContent('등록되었습니다.'));
					})
					
					$mdDialog.hide();
				}
				
				$scope.close = function() {
					$mdDialog.hide();
				}
			}
		});
	}
	
	$scope.goUserGroupView = function(userGroup) {
		$interval(function() {
			location.href = '/configuration/userGroups/' + userGroup.userGroupId;
		}, 200);
	}
});