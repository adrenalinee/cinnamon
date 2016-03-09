angular.module('cinnamon')
.controller('configuration.email.list', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia) {
	console.log('configuration.email.list');
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
		.then(
			function(result) {
				$scope.domains = result.data;
			}
			,function(error) {
				console.log('error');
			}
		)
	}
	
	// 기본 메일 등록
	$scope.setDefaultEmailServer = function(domain) {
		$http.patch('/rest/configuration/email/server/' + domain.emailServerId)
		.then (
			function(result) {
				if(result.status == '200') {
					$mdToast.show(
					    	$mdToast.simple()
					    	.textContent('기본서버로 설정되었습니다.')
					    	.position('top right')
					    	.hideDelay(3000)
						)
						$scope.load($scope.searchInfo);
				}else {
					$mdToast.show(
					    	$mdToast.simple()
					    	.textContent('기본서버 등록에 실패하였습니다.')
					    	.position('top right')
					    	.hideDelay(3000)
						)
				}
			},
			function(error) {
				$log.error("기본서버 설정시 Error!" + error);
			}
		)
	}
	
	$scope.load($scope.searchInfo);
	
});