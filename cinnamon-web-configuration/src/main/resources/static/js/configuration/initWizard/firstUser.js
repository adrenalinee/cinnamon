angular.module('cinnamon')
.controller('firstUserController', function($scope, $http, $state, toastr, $ngBootbox) {
	//
	$scope.create = function(form) {
		console.log('create');
		
		if (form.$valid == false) {
			toastr.warning('입력값을 확인하시기 바랍니다.');
			return;
		}
		
		$http.post('/rest/configuration/initWizard/firstUser', $scope.domain)
			.success(function(data, status) {
				$ngBootbox.customDialog({
					message: '<h3 class="text-success">축하합니다. 시스템 초기화가  성공적으로 마무리되었습니다.</h3>',
					buttons: {
						ok: {
							label: '시작하기!',
							callback: function() {
								//메인 페이지로이동시킴
								location.href = "/configuration";
							}
						}
					}
				});
			});
	}
});