angular.module('cinnamon')
.controller('configuration.file.view', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia, $stateParams) {
	console.log('configuration.file.view');
	// 화면 도메인
	$scope.domains;
	
	// 목록으로 이동
	$scope.goList = function() {
		$interval(function() {
			$state.go('list', {fileId: $stateParams.fileId});
		}, 200, 1);
	}
	
	// 초기 리스트 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/files/' + $stateParams.fileId)
			.success(function(result) {
				$scope.domains = result;
			})
	}
	
	// 초기 리스트 가져옴
	$scope.load();
	
});