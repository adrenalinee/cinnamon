angular.module('cinnamon')
.controller('configuration.email.server.modify', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia) {
	console.log('configuration.email.server.modify');
	// 화면 도메인
	$scope.emailServer;
	// 메일 테스트 버튼
	$scope.mailTestBtn = false;
	
	// 초기 리스트 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/email/server/' + $stateParams.emailServerId)
		.then(
			function(result) {
				$scope.emailServer = result.data;
			}
			,function(error) {
				console.log('error');
			}
		)
	}
	
	$scope.load();
	
	// 목록으로
	$scope.goView = function() {
		$interval(function() {
			$state.go('view', {emailServerId : $stateParams.emailServerId});
		}, 200, 1);
	}
	//
	$scope.modify = function() {
		var params = angular.copy($scope.emailServer);
		console.log(params);
		$http.put('/rest/configuration/email/server/' + $stateParams.emailServerId, params)
		.then(
			function(result) {
				console.log(result);
				$mdToast.show(
				    	$mdToast.simple()
				    	.textContent('수정되었습니다.')
				    	.position('top right')
				    	.hideDelay(3000)
				)
				$scope.goView();
			},
			function(error) {
				$log.error('이메일 서버 정보 수정 중 에러 ' + error);
			}
		)
	}
});

// 추후 템플리에트는 밑으로