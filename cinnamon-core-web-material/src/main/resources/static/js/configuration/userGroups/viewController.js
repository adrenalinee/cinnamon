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
	
	$scope.selectAuthority = function($event) {
		$mdDialog.show(
			$mdDialog.alert()
				.targetEvent($event)
				.title('권한 선택')
		);
	}
	
	$scope.selectUser = function($event) {
		$mdDialog.show(
			$mdDialog.alert()
				.targetEvent($event)
				.title('사용자 선택')
		);
	}
});