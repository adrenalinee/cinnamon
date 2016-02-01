angular.module('cinnamon')
.controller('configuration.users.view', function($scope, $http, $stateParams, $mdDialog) {
	console.log('configuration.users.view');
	
	var userId = $stateParams.userId;
	
	$http.get('/rest/configuration/users/' + userId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$scope.selectUserGroup = function($event) {
		$mdDialog.show(
			$mdDialog.alert()
				.title('사용자 모임 선택')
				.targetEvent($event)
		);
	}
});