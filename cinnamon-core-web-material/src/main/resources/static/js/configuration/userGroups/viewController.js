angular.module('cinnamon')
.controller('configuration.userGroups.view', function($scope, $http, $stateParams, $mdDialog) {
	console.log('configuration.userGroups.view');
	
	var userGroupId = $stateParams.userGroupId;
	$scope.userGroupId = userGroupId;
	
	$http.get('/rest/configuration/userGroups/' + userGroupId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$scope.selectPermission = function($event) {
		$mdDialog.show({
			targetEvent: $event,
			templateUrl: '/configuration/partials/roles/select',
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
		$mdDialog.show(
			$mdDialog.alert()
				.targetEvent($event)
				.title('사용자 선택')
		);
	}
});