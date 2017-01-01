angular.module('cinnamon')
.controller('configuration.roles.create', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state, $mdToast, message, pageMove) {

	// 등록
	$scope.create = function () {
		if($scope.frm.$valid == false){
			message.alert('입력값을 확인해 주세요.');
			return ;
		}
		// 파라미터
		var params = angular.copy($scope.role);
		console.log(params);
		$http.post('/rest/configuration/roles', params)
			.success(function(result, status, headers){
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