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
		.success(function(result) {
				$scope.domains = result;
		})
	}
	
	$scope.load();
	
	
	// 기본 메일 등록
	$scope.setDefaultEmailServer = function(emailServerId) {
		$http.patch('/rest/configuration/email/server/' + emailServerId)
			.success(function (result) {
				$mdToast.show(
						$mdToast.simple()
						.textContent('기본서버로 설정되었습니다.')
						.position('top right')
						.hideDelay(3000)
					)
				$scope.load();
			})
	}
	
	// 메일 발송 테스트
	$scope.mailTest = function(emailServerId) {
		
		if($scope.frm.$valid == false){
			$mdToast.show(
				$mdToast.simple()
					.textContent('입력값을 확인해 주세요.')
					.position('top right')
					.hideDelay(3000)
			);
			return ;
		}
		
		$scope.mailTestBtn = true; 
		$http.get('/rest/configuration/email/server/' + emailServerId + '/test?toAddress=' + $scope.toAddress)
			.success(function (result) {
				console.log(result);
				$mdToast.show(
					$mdToast.simple()
					.textContent('테스트가 완료되었습니다.')
					.position('top right')
					.hideDelay(3000)
				)
				$scope.mailSendTestMsg = result.msg;
				$scope.mailTestBtn = false;
			})
	}
	
	// 목록으로
	$scope.goList = function() {
		$interval(function() {
			$state.go('list');
		}, 200, 1);
	}
	
	// 삭제
	$scope.delete = function(emailServerId) {
		var confirm = $mdDialog.confirm()
			.title('삭제')
			.textContent('서버 정보를 삭제하시겠습니까?')
			.ok('삭제')
			.cancel('취소');
		$mdDialog.show(confirm).then(function() {
			// 삭제
			$http.delete('/rest/configuration/email/server/' + emailServerId)
				.success(function(result) {
					$mdToast.show(
							$mdToast.simple()
							.textContent("삭제되었습니다.")
							.position("top right")
							.hideDelay(3000)
					)
					// 목록으로
					$scope.goList();
				})
		}, function() {
			// 취소
			
		});
	}
});
// 추후 템플리에트는 밑으로