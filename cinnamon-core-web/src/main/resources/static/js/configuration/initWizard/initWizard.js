'use strict'

angular.module('cinnamon.initWizard', ['cinnamon', 'mdSteppers'])
.controller('wizardController', function($scope, $http, $mdToast, $mdDialog, $mdStepper) {
	
	$scope.goBaseData = function() {
		console.log('goBaseData');
		$mdStepper('initWizardStepper').next();
	}
	
	$scope.createBaseData = function() {
		console.log('createBaseData');
		$http.post('/rest/configuration/initWizard/baseData')
		.success(function(data) {
			$mdStepper('initWizardStepper').next();
		});
	}
	
	$scope.createFirstUser = function(form, domain, $event) {
		console.log('createFirstUser');
		
		if (form.$valid == false) {
//			toastr.warning('입력값을 확인하시기 바랍니다.');
			$mdToast.show(
					$mdToast.simple()
					.position('top right')
					.textContent('입력값을 확인하시기 바랍니다.'));
			return;
		}
		
		console.log($scope.domain);
		$http.post('/rest/configuration/initWizard/firstUser', domain)
			.success(function(data, status) {
				$mdDialog.show(
					$mdDialog.alert()
					.targetEvent($event)
					.textContent('축하합니다. 시스템 초기화가  성공적으로 마무리되었습니다.')
					.ok('시작하기!'))
				.finally(function() {
					//메인 페이지로이동시킴
					location.href = "/";
				});
				
				
				/* $ngBootbox.customDialog({
					message: '축하합니다. 시스템 초기화가  성공적으로 마무리되었습니다.',
					buttons: {
						ok: {
							label: '시작하기!',
							callback: function() {
								//메인 페이지로이동시킴
								location.href = "/configuration";
							}
						}
					}
				}); */
			});
	}
});