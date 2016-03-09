angular.module('cinnamon')
.controller('configuration.email.server.view', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia) {
	console.log('configuration.email.server.view');
	// 화면 도메인
	$scope.domains;
	// 메일 테스트 버튼
	$scope.mailTestBtn = false;
	
	// 초기 리스트 가져오기
	$scope.load = function() {
		$http.get('/rest/configuration/email/server/' + $stateParams.emailServerId)
		.then(
			function(result) {
				$scope.domains = result.data;
			}
			,function(error) {
				console.log('error');
			}
		)
	}
	
	$scope.load();
	
	
	// 기본 메일 등록
	$scope.setDefaultEmailServer = function(emailServerId) {
		$http.patch('/rest/configuration/email/server/' + emailServerId)
		.then (
			function(result) {
				if(result.status == '200') {
					$mdToast.show(
					    	$mdToast.simple()
					    	.textContent('기본서버로 설정되었습니다.')
					    	.position('top right')
					    	.hideDelay(3000)
						)
						$scope.load();
				}else {
					$mdToast.show(
					    	$mdToast.simple()
					    	.textContent('기본서버 등록에 실패하였습니다.')
					    	.position('top right')
					    	.hideDelay(3000)
						)
				}
			},
			function(error) {
				$log.error("기본서버 설정시 Error!" + error);
			}
		)
	}
	
	// 메일 발송 테스트
	$scope.mailTest = function(emailServerId) {
		$scope.mailTestBtn = true; 
		$http.get('/rest/configuration/email/server/' + emailServerId + '/test')
		.then(
			function (result) {
				console.log(result);
				$mdToast.show(
				    	$mdToast.simple()
				    	.textContent('테스트가 완료되었습니다.')
				    	.position('top right')
				    	.hideDelay(3000)
				)
				$scope.mailSendTestMsg = result.data.msg;
				$scope.mailTestBtn = false;
			},
			function (error) {
				$mdToast.show(
				    	$mdToast.simple()
				    	.textContent('정상적으로 완료되지 않았습니다. 서버 정보를 확인해주세요.')
				    	.position('top right')
				    	.hideDelay(3000)
				)
				$log.error('테스트 메일 발송 중 error! ' + error);
				$scope.mailTestBtn = false;
			}
		)
	}
	
	// 목록으로
	$scope.goList = function() {
		$interval(function() {
			$state.go('list');
		}, 200, 1);
	}
	
	$scope.delete = function(emailServerId) {
	    var confirm = $mdDialog.confirm()
	        .title('삭제')
	        .textContent('서버 정보를 삭제하시겠습니까?')
	        .ok('삭제')
	        .cancel('취소');
	  $mdDialog.show(confirm).then(function() {
	    // 삭제
		  $http.delete('/rest/configuration/email/server/' + emailServerId)
		  	.then(
		  			function(result) {
		  				$mdToast.show(
		  						$mdToast.simple()
		  						.textContent("삭제되었습니다.")
		  						.position("top right")
		  						.hideDelay(3000)
		  				)
		  				// 목록으로
		  				$scope.goList();
		  			},
		  			function(error) {
		  				
		  			}
				  )
	  }, function() {
	    // 취소
		  
	  });
	}
});

// 추후 템플리에트는 밑으로