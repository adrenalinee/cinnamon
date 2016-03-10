angular.module('cinnamon')
.controller('configuration.roles.create', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state, $mdToast) {

	// 등록
	$scope.create = function () {
		if($scope.frm.$valid == false){
			$mdToast.show(
					$mdToast.simple()
						.textContent('입력값을 확인해 주세요.')
						.position('top right')
						.hideDelay(3000)
			);
			return ;
		}
		// 파라미터
		var params = angular.copy($scope.role);
		console.log(params);
		$http.post('/rest/configuration/roles', params)
			.success(function(result, status, headers){
					$mdToast.show(
							$mdToast.simple()
								.textContent('등록되었습니다.')
								.position('top right')
								.hideDelay(3000)
					);

					$interval(function() {
						var viewLink = headers("Location");
						location.href = viewLink;
					}, 200, 1);
			})
	}
	
	// 리스트로 돌아가기
	$scope.goList = function() {
		$interval(function() {
			$state.go('list');
		}, 200, 1);
	}

});