angular.module('cinnamon')
.controller('configuration.email.server.list', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia) {
	console.log('configuration.email.server.list');
	// 화면 도메인
	$scope.domains;
	$scope.searchInfo = {
			sort: 'createdAt,desc'
	};
	
	// 기본 등록 버튼 'server'
	$scope.showCreateBtn = 'server';
	
	// 상세 페이지 이동
	$scope.goView = function(domain) {
		$interval(function() {
			$state.go('view', {emailServerId: domain.emailServerId});
		}, 200, 1);
	}
	
	// 작성 페이지 이동
	$scope.goWrite = function() {
		$interval(function() {
			$state.go('create');
		}, 200, 1);
	}
	
	// 초기 리스트 가져오기
	$scope.load = function(params) {
		$http.get('/rest/configuration/email/servers', {params : params})
		.success(function(result) {
				$scope.domains = result;
		})
	}
	
	// 기본 메일 등록
	$scope.setDefaultEmailServer = function(domain) {
		$http.patch('/rest/configuration/email/server/' + domain.emailServerId)
		.success (function(result) {
				$mdToast.show(
						$mdToast.simple()
						.textContent('기본서버로 설정되었습니다.')
						.position('top right')
						.hideDelay(3000)
					)
				$scope.load($scope.searchInfo);
		})
	}
	
	$scope.load($scope.searchInfo);
	
});