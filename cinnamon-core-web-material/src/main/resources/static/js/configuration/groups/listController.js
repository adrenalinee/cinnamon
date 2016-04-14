angular.module('cinnamon')
.controller('configuration.group.list', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia, pageMove) {
	console.log('configuration.group.list');
	// 화면 도메인
	$scope.domains;
	$scope.searchInfo = {
		sort : 'parentGroupId,asc'
			//sort : 'groupId,asc'
	};
	
	// 기본 등록 버튼 'server'
	$scope.showCreateBtn = 'server';
	
	// 상세 페이지 이동
	$scope.goView = function(domain) {
		pageMove.go('view', {groupId: domain.groupId, page : $scope.searchInfo.page})
	}
	
	// 작성 페이지 이동
	$scope.goWrite = function() {
		$interval(function() {
			$state.go('create');
		}, 200, 1);
	}
	
	// 초기 리스트 가져오기
	$scope.search = function(params) {
		$http.get('/rest/configuration/groups', {params : params})
		.success(function(result) {
			$scope.domains = result;
		})
	}
	
	// 초기 리스트 가져옴
	$scope.search($scope.searchInfo);
	
});