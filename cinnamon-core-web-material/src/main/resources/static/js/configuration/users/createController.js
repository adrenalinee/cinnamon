angular.module('cinnamon')
.controller('configuration.users.create', function($scope, $http, $interval, $state, $mdToast, $mdDialog, $mdMedia) {
	console.log('configuration.users.create');
	
	$scope.create = function(form, event) {
		console.log('create');
		
		if (form.$valid == false ||
			$scope.domain.password != $scope.domain.password2) {
			
			$mdToast.show(
				$mdToast.simple()
					.textContent('입력값을 확인하시기 바랍니다.'));
			return;
		}
		
		$scope.isProcess = true;
		$http.head('/rest/configuration/users/' + $scope.domain.userId)
		.success(function(data, status) {
			$mdToast.show(
					$mdToast.simple()
					.position('top right')
					.textContent('이미 사용중인 아이디 입니다. 다른 아이디를 입력하시기 바랍니다.'));
			$scope.isProcess = false;
		}).error(function(data, status) {
			if (status == 404) {
				$scope.isProcess = true;
				$http.post('/rest/configuration/users', $scope.domain)
				.success(function(data, status, config) {
					console.log(status);
					
					$mdDialog.hide()
					.then(function() {
						$state.go('view', {userId: $scope.domain.userId});
					});
				});
			}
		});
		
	}
	
	$scope.goBack = function() {
		history.go(-1);
	}
	
	$scope.close = function() {
		$mdDialog.hide();
	}
	
	$scope.isMobile = function() {
		return !$mdMedia('gt-sm');
	}
});