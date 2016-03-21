angular.module('cinnamon')
.controller('configuration.userGroups.modify', function($scope, $http, $interval, $state, $stateParams, $mdToast, $mdDialog) {
	console.log('configuration.userGroups.modify');
	
	var userGroupId = $stateParams.userGroupId;
	$scope.userGroupId = userGroupId;
	
	$http.get('/rest/configuration/userGroups/' + userGroupId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$scope.goView = function() {
		history.go(-1);
	}
	
	$scope.update = function(form, event) {
		
		if (form.$valid == false) {
			$mdToast.show(
				$mdToast.simple()
				.position('top right')
				.textContent('입력값을 확인하시기 바랍니다.'));
			
			return;
		}
		
		$scope.isProcess = true;
		$http.put('/rest/configuration/userGroups/' + userGroupId, $scope.domain)
		.success(function(data) {
			
			$state.go('view', {userGroupId: userGroupId}, {
				location: 'replace'
			});
		}).finally(function(data) {
			$scope.isProcess = false;
		});
		
	}
});