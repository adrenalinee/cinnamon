angular.module('cinnamon')
.controller('configuration.roles.modify', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state, $mdToast, message, pageMove) {

	// role 정보 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/roles/' + $stateParams.permissionId)
		.success(function(result) {
				$log.info(result);
				$scope.role = result;
		})
	}
	
	// role 정보 가져오기
	$scope.load();
	
	// 등록
	$scope.modify = function () {
		if($scope.frm.$valid == false){
			message.alert('입력값을 확인해 주세요.');
			return ;
		}
		// 파라미터
		var params = angular.copy($scope.role);
		console.log(params);
		$http.put('/rest/configuration/roles/' + $scope.role.permissionId, params)
			.success(function(result){
				message.alert('수정되었습니다.');
				pageMove.go('view', {permissionId : $scope.role.permissionId});
			})
	}
	
	// 리스트로 돌아가기
	$scope.goList = function() {
		pageMove.go('list');
	}

});