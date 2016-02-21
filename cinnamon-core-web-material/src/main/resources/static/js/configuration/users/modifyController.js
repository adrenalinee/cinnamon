angular.module('cinnamon')
.controller('configuration.users.modify', function($scope, $http, $interval, $state, $stateParams, $mdDialog) {
	console.log('configuration.users.modify');
	
	var userId = $stateParams.userId;
	$scope.userId = userId;
	
	$http.get('/rest/configuration/users/' + userId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	$http.get("/rest/configuration/groups/nations/childs")
	.success(function(data) {
		console.log(data);
		$scope.nations = data;
		
		$scope.nations.sort(function(a, b) {
			return a.name > b.name ? 1 : a.name < b.name ? -1: 0;
		});
	});
	
	
	
	$scope.goView = function() {
		$interval(function() {
			$state.go('view', {userId: userId});
		}, 200, 1);
	}
	
	$scope.update = function(form, event) {
		$interval(function() {
			$state.go('view', {userId: userId}, {
				location: 'replace'
			});
		}, 200, 1);
	}
});