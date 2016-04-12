angular.module('cinnamon')
.controller('configuration.menuGroups.create', function($scope, $http, $interval, $state, $location, $mdToast, $mdDialog, message) {
	console.log('configuration.menuGroups.create');
	
	var siteId = $location.search().siteId;
	
	$scope.create = function(form, event) {
		console.log('create');
		
		if (form.$valid == false) {
			message.alert('입력값을 확인하시기 바랍니다.');
			return;
		}
		
		
		$scope.isProcess = true;
		$scope.domain.siteId = siteId;
		$http.post('/rest/configuration/menuGroups', $scope.domain)
		.success(function(data, status, headers) {
			console.log(status);
			
			var location = headers("Location");
			$http.get(location)
			.success(function(data) {
				console.log(data);
				
				$state.go('view', {menuGroupId: data.menuGroupId});
			});
		}).error(function() {
			$scope.isProcess = false;
		});
		
	}
	
	$scope.goBack = function() {
		history.go(-1);
	}
	
});