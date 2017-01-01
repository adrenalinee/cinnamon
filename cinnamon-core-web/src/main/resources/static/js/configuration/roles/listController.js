angular.module('cinnamon')
.controller('configuration.roles.list', function($scope, $http, $interval, $state, pageMove) {
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
		pageMove.go('create');
	}
	
});