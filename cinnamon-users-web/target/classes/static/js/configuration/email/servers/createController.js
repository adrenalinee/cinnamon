angular.module('cinnamon')
.controller('configuration.email.server.create', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia, message, pageMove) {
	console.log('configuration.email.server.create');
	
	// 등록
	$scope.create = function() {
		
		if($scope.frm.$valid == false){
			message.alert('입력값을 확인해주세요.');
			return ;
		}
		// 파라미터
		var params = angular.copy($scope.emailServer);
		console.log(params);
		$http.post('/rest/configuration/email/servers', params)
			.success(function(result, status, headers) {
					message.alert('등록되었습니다.');
					
					$interval(function() {
						var viewLink = headers("Location");
						location.href = viewLink;
					}, 200, 1);
			})
	}
	
	// 리스트로 돌아가기
	$scope.goList = function() {
		pageMove.go('list');
	}
	
});