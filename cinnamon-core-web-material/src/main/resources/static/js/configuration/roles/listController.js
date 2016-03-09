angular.module('cinnamon')
.controller('configuration.roles.list', function($scope, $http, $interval, $state) {
	console.log('configuration.roles.list');
	// 화면 도메인
	$scope.domains;
	$scope.searchInfo = {
			sort: 'permissionId,desc'
	};

	$scope.goView = function(domain) {
		$interval(function() {
			$state.go('view', {permissionId: domain.permissionId});
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
		$http.get('/rest/configuration/roles', {params : params})
		.then(
			function(result) {
				$scope.domains = result.data;
			}
			,function(error) {
				console.log('error');
			}
		)
	}
	
	$scope.load($scope.searchInfo);

});