angular.module('cinnamon')
.controller('configuration.roles.modify', function($scope, $http, $interval, $stateParams, $log, $mdDialog, $mdMedia, $state, $mdToast) {

	// role 정보 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/roles/' + $stateParams.permissionId)
		.then(
			function(result) {
				$log.info(result.data);
				$scope.role = result.data;
			}
			,function(error) {
				$log.error('error');
			}
		)
	}
	
	// role 정보 가져오기
	$scope.load();
	
	// 등록
	$scope.modify = function () {
		if($scope.frm.$valid == false){
		    $mdToast.show(
		    	      $mdToast.simple()
		    	        .textContent('입력값을 확인해 주세요.')
		    	        .position('top right')
		    	        .hideDelay(3000)
		    );
			return ;
		}
		// 파라미터
		var params = angular.copy($scope.role);
		console.log(params);
		$http.put('/rest/configuration/roles/' + $scope.role.permissionId, params).then(
				function(result){
				    $mdToast.show(
				    	      $mdToast.simple()
				    	        .textContent('수정되었습니다.')
				    	        .position('top right')
				    	        .hideDelay(3000)
				    );
				    
					$interval(function() {
						$state.go('view', {permissionId : $scope.role.permissionId});
					}, 200, 1);
					
				},
				function(error){
					$log.error("역할 및 권한 등록 Error! " + error);
				}
		)
	}
	
	// 리스트로 돌아가기
	$scope.goList = function() {
		$interval(function() {
			$state.go('list');
		}, 200, 1);
	}

});