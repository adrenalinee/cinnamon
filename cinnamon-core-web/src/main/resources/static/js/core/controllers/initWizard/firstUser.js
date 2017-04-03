angular.module('cinnamon')
.controller('firstUserCtrl', function($scope, $http, $mdStepper, $mdDialog, $mdToast) {
	
	$scope.createFirstUser = function(form, $event) {
		console.log('createFirstUser');
		
		console.log(form);
		
		if (form.$valid == false) {
//			toastr.warning('입력값을 확인하시기 바랍니다.');
			$mdToast.show(
				$mdToast.simple()
				.textContent('입력값을 확인하시기 바랍니다.'));
			return;
		}
		
		
		$scope.isProcess = true;
		$http.post('/rest/core/initWizard/firstUser', $scope.domain)
		.then(function(response) {
			$mdDialog.show(
				$mdDialog.alert()
				.targetEvent($event)
				.textContent('축하합니다. 시스템 초기화가  성공적으로 마무리되었습니다.')
				.ok('시작하기!'))
			.finally(function() {
				//메인 페이지로이동시킴
				location.href = "/";
			});
		}).finally(function() {
			$scope.isProcess = false;
		});
	}
});