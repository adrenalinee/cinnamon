angular.module('cinnamon')
.controller('configuration.userGroups.view', function($scope, $http, $stateParams) {
	console.log('configuration.userGroups.view');
	
	var userGroupId = $stateParams.userGroupId;
	
	$http.get('/rest/configuration/userGroups/' + userGroupId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
});