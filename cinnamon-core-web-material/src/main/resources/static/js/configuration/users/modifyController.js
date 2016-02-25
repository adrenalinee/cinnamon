angular.module('cinnamon')
.controller('configuration.users.modify', function($scope, $http, $interval, $state, $stateParams, $mdToast, $mdDialog) {
	console.log('configuration.users.modify');
	
	var userId = $stateParams.userId;
	$scope.userId = userId;
	
	$http.get('/rest/configuration/users/' + userId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$http.get('/rest/configuration/groups/nations/childs')
	.success(function(data) {
		console.log(data);
		$scope.nations = data;
		
		$scope.nations.sort(function(a, b) {
			return a.name > b.name ? 1 : a.name < b.name ? -1: 0;
		});
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
		$http.put('/rest/configuration/users/' + userId, $scope.domain)
		.success(function(data) {
			
			
			$state.go('view', {userId: userId}, {
				location: 'replace'
			});
		}).finally(function(data) {
			$scope.isProcess = false;
		});
		
		
		
//		$state.go('view', {userId: userId}, {
//			location: 'replace'
//		});
	}
});