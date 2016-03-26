angular.module('cinnamon')
.controller('configuration.group.create', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia, $compile) {
	console.log('configuration.group.create');
	
	// 코드 생성
	$scope.create = function() {
		if(!$scope.frm.$valid) {
			$mdToast.show(
					$mdToast.simple()
						.textContent('입력값을 확인해 주세요.')
						.position('top right')
						.hideDelay(3000)
			);
			return ;
		}
		
		var params = angular.copy($scope.group);
		$http.post('/rest/configuration/groups', params)
			.success(function(result, status, headers) {
				$mdToast.show(
					$mdToast.simple()
						.textContent('등록되었습니다.')
						.position('top right')
						.hideDelay(3000)
				);
				$interval(function() {
					location.href = headers("Location");
				}, 200, 1);
				
			})
			.error(function(data) {
				$mdToast.show(
					$mdToast.simple()
						.textContent('중복된 groupId 입니다.')
						.position('top right')
						.hideDelay(3000)
				);
		})
	}
	
	// 리스트로 돌아가기
	$scope.goList = function() {
		$interval(function() {
			$state.go('list');
		}, 200, 1);
	}
});