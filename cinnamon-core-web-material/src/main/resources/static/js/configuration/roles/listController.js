angular.module('cinnamon')
.controller('configuration.roles.list', function($scope, $http, $interval, $state) {
	console.log('configuration.roles.list');
	// 화면 도메인
	$scope.domains;
	// 검색 객체
	$scope.searchInfo = {};	
	
	$scope.goView = function(permissionId) {
		$interval(function() {
			$state.go('view', {permissionId: permissionId});
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
	
	// 페이징 이동
	$scope.onPageChange = function() {
		console.log('onPageChange');
		$scope.search();
	}
});