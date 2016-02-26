angular.module('cinnamon')
.controller('configuration.userGroups.create', function($scope, $http, $interval, $state, $mdToast, $mdDialog) {
	console.log('configuration.userGroups.create');
	
	$scope.create = function(form, event) {
		console.log('create');
		
		if (form.$valid == false) {
			
			$mdToast.show(
					$mdToast.simple()
					.position('top right')
					.textContent('입력값을 확인하시기 바랍니다.'));
			return;
		}
		
		$http.post('/rest/configuration/userGroups', $scope.domain)
		.success(function(data, status, headers) {
			console.log(status);
			
			var location = headers("Location");
			$http.get(location)
			.success(function(data) {
				console.log(data);
				
				$state.go('view', {userGroupId: data.userGroupId});
			});
		});
		
	}
	
	$scope.goBack = function() {
		history.go(-1);
	}
	
});