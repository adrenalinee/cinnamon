angular.module('cinnamon')
.controller('configuration.email.server.list', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia, message, pageMove) {
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
		pageMove.go('view', {emailServerId: domain.emailServerId});
	}
	
	// 작성 페이지 이동
	$scope.goWrite = function() {
		pageMove.go('create');
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
			message.alert('기본서버로 설정되었습니다.');
			$scope.load($scope.searchInfo);
		})
	}
	
	$scope.load($scope.searchInfo);
	
});