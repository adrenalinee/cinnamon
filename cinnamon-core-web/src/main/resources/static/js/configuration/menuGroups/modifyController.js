angular.module('cinnamon')
.controller('configuration.menuGroups.modify', function($scope, $http, $stateParams, $mdDialog, $mdMedia, $mdToast, $state) {
	console.log('configuration.menuGroups.modify');
	
	var menuGroupId = $stateParams.menuGroupId;
	$scope.menuGroupId = menuGroupId;
	
	$scope.load = function() {
		$http.get('/rest/configuration/menuGroups/' + menuGroupId)
		.success(function(data) {
			console.log(data);
			
			$scope.domain = data;
		});
	}
	
	$scope.load();
	
	
	$scope.update = function(form, event) {
		console.log('update');
		
		if (form.$valid == false) {
			$mdToast.show(
					$mdToast.simple()
					.position('top right')
					.textContent('입력값을 확인하시기 바랍니다.'));
			return;
		}
		
		
		$scope.isProcess = true;
		$http.put('/rest/configuration/menuGroups/' + menuGroupId, $scope.domain)
		.success(function(data, status, headers) {
			console.log(status);
			
			$mdToast.show(
				$mdToast.simple()
					.position('top right')
					.textContent('수정되었습니다.'));
			
			$state.go('view', {menuGroupId: menuGroupId}, {
				location: 'replace'
			});
		}).finally(function(data) {
			$scope.isProcess = false;
		});
		
	}
	
	$scope.goBack = function() {
		history.go(-1);
	}
});