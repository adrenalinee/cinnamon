angular.module('cinnamon')
.controller('configuration.userGroups.view', function($scope, $http, $stateParams, $mdDialog, $mdMedia) {
	console.log('configuration.userGroups.view');
	
	var userGroupId = $stateParams.userGroupId;
	$scope.userGroupId = userGroupId;
	
	$http.get('/rest/configuration/userGroups/' + userGroupId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$scope.selectPermission = function($event) {
		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
		$mdDialog.show({
			targetEvent: $event,
			fullscreen: useFullScreen,
			clickOutsideToClose: true,
			templateUrl: '/configuration/partials/roles/select',
			controller: function($scope, $http, $mdDialog, $mdToast) {
				$scope.close = function() {
					$mdDialog.hide();
				}
				
				$scope.onSelect = function(permission) {
					console.log('onSelect');
					
					$http.put('/rest/configuration/userGroups/' + userGroupId + '/permission', {
						permissionId: permission.permissionId
					}).success(function(data) {
						$mdToast.show(
								$mdToast.simple()
								.position('top right')
								.textContent('변경되었습니다.'));
						
						//TODO 사용자 목록을 새로 불러와야 함 
					})
					
					$mdDialog.hide();
				}
				
				$scope.close = function() {
					$mdDialog.hide();
				}
			}
		});
	}
	
	$scope.selectUser = function($event) {
		var useFullScreen = $mdMedia('sm') || $mdMedia('xs');
		$mdDialog.show({
			targetEvent: $event,
			fullscreen: useFullScreen,
			clickOutsideToClose: true,
			templateUrl: '/configuration/partials/users/select',
			controller: function($scope, $http, $mdDialog, $mdToast) {
				$scope.onSelect = function(user) {
					console.log('onSelect');
					
					$http.put('/rest/configuration/userGroups/' + userGroupId + '/users', {
						userId: user.userId
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
});