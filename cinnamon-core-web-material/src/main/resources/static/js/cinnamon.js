'use strict'

angular.module(
		'cinnamon',
		[
		// 'ngResource',
		'ngMessages',
		'ngAnimate', 
		'ngAria', 
		'ngSanitize', 
		'ui.router',
		'ngMaterial', 
		'ui.bootstrap.paging', 
		'ui.bootstrap.pagination',
		'ui.bootstrap.pager', 
		'ngFileUpload', 
		'mdPickers',
		'angularMoment'
		])
.config(function($locationProvider, $httpProvider, $mdThemingProvider) {
	var token;
	var header;

	angular.forEach(angular.element(document).find('meta'), function(
			value, key) {
		// console.log(value);

		if (value.name == '_csrf') {
			token = value.content;
		} else if (value.name == '_csrf_header') {
			header = value.content;
		}
	});
	
	$httpProvider.defaults.headers.common[header] = token;
	$httpProvider.interceptors.push(function($q, $injector) {
		return {
			'responseError': function(rejection) {
				console.log('responseError!');
				
				var $mdDialog = $injector.get('$mdDialog');
				
				if (rejection.status == 400) {
					if (rejection.data != null) {
						if (rejection.data.violations != null) {
							var message = '';
							
							rejection.data.violations.forEach(function(v) {
								message += '<b>' + v.paramName + '</b> :';
								message += v.message + '<br />';
							});
							
							$mdDialog.show(
								$mdDialog.alert()
									.title('입력 오류')
									.htmlContent(message)
									.ok('확인'));
						}
					}
				} else if (rejection.status == 401) {
					$mdDialog.show(
						$mdDialog.alert()
							.title('인증 오류')
							.htmlContent('로그인해주시기 바랍니다.')
							.ok('확인'));
				} else if (rejection.status == 403) {
					$mdDialog.show(
						$mdDialog.alert()
							.title('숨겨진 자원 접근 오류')
							.htmlContent('권한이 없습니다.')
							.ok('확인'));
				} else if (rejection.status == 500) {
					$mdDialog.show(
						$mdDialog.alert()
							.title('서버 오류')
							.htmlContent(rejection.data)
							.ok('확인'));
				}
				
				
				return $q.reject(rejection);
			}
		}
	});

	$locationProvider.html5Mode({
		enabled : true,
	// requireBase: false
	});

	$mdThemingProvider.theme('default');
	/*
	// 공통 Error 처리 추가
	$httpProvider.interceptors.push(function($q) {
		return {
			'responseError' : function(response) {
				alert('Error : ' + response.data.error);
			}
		}
	})
	*/
	// $mdThemingProvider.definePalette('amazingPaletteName', {
	// '50': 'ffebee',
	// '100': 'ffcdd2',
	// '200': 'ef9a9a',
	// '300': 'e57373',
	// '400': 'ef5350',
	// '500': 'f44336',
	// '600': 'e53935',
	// '700': 'd32f2f',
	// '800': 'c62828',
	// '900': 'b71c1c',
	// 'A100': 'ff8a80',
	// 'A200': 'ff5252',
	// 'A400': 'ff1744',
	// 'A700': 'd50000',
	// 'contrastDefaultColor': 'light', // whether, by default, text
	// (contrast)
	// // on this palette should be dark or light
	// 'contrastDarkColors': ['50', '100', //hues which contrast should
	// be 'dark' by default
	// '200', '300', '400', 'A100'],
	// 'contrastLightColors': undefined // could also specify this if
	// default was 'dark'
	// });
	// $mdThemingProvider.theme('default')
	// .primaryPalette('amazingPaletteName')
})
// 사용자 정보 세션
.service('session', ['$rootScope', function($rootScope) {
	$rootScope.userInfo = userInfo;
	
	this.getSession = function() {
		return $rootScope.userInfo;
	}
	this.reset = function() {
		$rootScope.userInfo = {};
	}
}])
.filter('newlines', function() {
	return function(text) {
		return text.replace(/\n/g, '<br />');
	}
});
	