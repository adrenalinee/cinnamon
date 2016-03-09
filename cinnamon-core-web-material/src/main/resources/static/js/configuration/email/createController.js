angular.module('cinnamon')
.controller('configuration.email.server.create', function($scope, $http, $interval, $state, $log, $mdToast, $mdDialog, $mdMedia) {
	console.log('configuration.email.server.create');
	
	// 등록
	$scope.create = function () {
		
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
		var params = angular.copy($scope.emailServer);
		console.log(params);
		$http.post('/rest/configuration/email/servers',params).then(
				function(result){
				    $mdToast.show(
				    	      $mdToast.simple()
				    	        .textContent('등록되었습니다.')
				    	        .position('top right')
				    	        .hideDelay(3000)
				    );

					$interval(function() {
						var viewLink = result.headers("Location");
						location.href = viewLink;
					}, 200, 1);
				},
				function(error){
					$log.error("메일 서버 등록 Error! " + error);
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