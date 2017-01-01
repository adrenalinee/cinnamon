angular.module('cinnamon')
.controller('configuration.group.create', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia, $compile, message, pageMove) {
	console.log('configuration.group.create');
	
	// 코드 생성
	$scope.create = function() {
		if(!$scope.frm.$valid) {
			message.alert('입력값을 확인해 주세요.');
			return ;
		}
		
		var params = angular.copy($scope.group);
		$http.post('/rest/configuration/groups', params)
			.success(function(result, status, headers) {
				message.alert('등록되었습니다.');
				
				$interval(function() {
					location.href = headers("Location");
				}, 200, 1);
				
			})
			.error(function(data) {
				message.alert('중복된 groupId 입니다.');
			})
	}
	
	// 리스트로 돌아가기
	$scope.goList = function() {
		pageMove.go('list');
	}
});