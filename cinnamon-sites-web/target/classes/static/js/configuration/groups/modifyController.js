angular.module('cinnamon')
.controller('configuration.group.modify', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia, $compile, message, pageMove) {
	console.log('configuration.group.modify');
	
	// 화면 도메인
	$scope.group;
	
	// 기본 정보
	$scope.load = function() {
		$http.get('/rest/configuration/groups/' + $stateParams.groupId)
			.success(function(result) {
				console.log(result);
				$scope.group = result;
			})
	}
	$scope.load();
	
	// 수정하기
	$scope.modify = function() {
		if(!$scope.frm.$valid) {
			message.alert('입력값을 확인해 주세요.');
		}
		
		var params = angular.copy($scope.group);
		$http.put('/rest/configuration/groups/' + $scope.group.groupId, params)
			.success(function(result) {
				message.alert('수정되었습니다.');
				$scope.goView();
			})
	}
	
	// 리스트로 돌아가기
	$scope.goView = function() {
		pageMove.go('view',{groupId : $stateParams.groupId});
	}
});