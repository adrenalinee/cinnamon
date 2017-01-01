angular.module('cinnamon')
.controller('configuration.file.list', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia, pageMove) {
	console.log('configuration.file.list');
	// 화면 도메인
	$scope.domains;
	$scope.searchInfo = {
		sort : 'createdAt,desc'
	};
	
	// 상세 페이지 이동
	$scope.goView = function(domain) {
		console.log('view')
		pageMove.go('view',  {fileId: domain.fileId})
	}
	
	// 초기 리스트 가져오기
	$scope.search = function(params) {
		$http.get('/rest/configuration/files', {params : params})
		.success(function(result) {
			$scope.domains = result;
		})
	}
	
	// 초기 리스트 가져옴
	$scope.search($scope.searchInfo);
	
});