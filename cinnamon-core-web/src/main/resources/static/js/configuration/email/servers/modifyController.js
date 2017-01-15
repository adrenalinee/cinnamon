angular.module('cinnamon')
.controller('configuration.email.server.modify', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia, message, pageMove) {
	console.log('configuration.email.server.modify');
	// 화면 도메인
	$scope.emailServer = {};
	// 메일 테스트 버튼
	$scope.mailTestBtn = false;
	
	// 초기 리스트 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/email/server/' + $stateParams.emailServerId)
		.success(function(result) {
			$scope.emailServer = result;
		})
	}
	
	$scope.load();
	
	// 목록으로
	$scope.goView = function() {
		pageMove.go('view', {emailServerId : $stateParams.emailServerId});
	}
	//
	$scope.modify = function() {
		var params = angular.copy($scope.emailServer);
		console.log(params);
		$http.put('/rest/configuration/email/server/' + $stateParams.emailServerId, params)
			.success(function(result) {
						console.log(result);
						message.alert('수정되었습니다.');
						$scope.goView();
			})
	}
});

// 추후 템플리에트는 밑으로