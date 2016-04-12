'use strict'

angular.module('cinnamon', [
//		'ngResource',
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
	'mdPickers'
	
])
.factory('groupService', function($http) {
	return {
		getGroupMap : function(groupId, callback) {
			$http.get('/rest/platform/groups/' + groupId + '/childs')
				.success(function(result) {
					console.log(result);
					callback(result);
				})
		}
	}
})
// 단순 얼럿 처리
.factory('message', function($mdToast, $mdDialog) {
	return {
			alert: function(text) {
				$mdToast.show(
					$mdToast.simple()
					.textContent(text)
					.position('right top')
					.hideDelay(3000)
				)
			}
			/*
			,confirm : function(message, fn) {
				var confirm = $mdDialog.confirm()
				.title('삭제')
				.textContent(message)
				.ok('삭제')
				.cancel('취소');
				$mdDialog.show(confirm)
					.then(  fn() 
							, function() {
					// 취소
					
					});
			}
			*/
	}
})
// 페이지 이동
.factory('pageMove', function($interval, $state) {
	return {
		go : function() {
			var arg = arguments;
			$interval(function() {
				if(arg.length == 1) {
					$state.go(arg[0]);	
				}else if(arg.length == 2){
					$state.go(arg[0], arg[1]);
				}
			}, 150, 1);
		}
	}
})
/*.factory('defaultErrorInterceptor', function($q) {
	return {
		'responseError': function(rejection) {
			console.log('responseError');
			console.log(rejection);
			
			$mdDialog.show(
				$mdDialog.alert()
					.title('오류')
					.textContent(rejection.data.message)
					.ok('확인')
			);
			
			
			return $q.reject(rejection);
		}
	};
})
*/.config(function($locationProvider, $httpProvider, $mdThemingProvider) {
	var token;
	var header;
	
	angular.forEach(
		angular.element(document).find('meta'),
		function(value, key) {
//			console.log(value);
			
			if (value.name == '_csrf') {
				token = value.content;
			} else if (value.name == '_csrf_header') {
				header = value.content;
			}
		});
	
	
//	var token = angular.element(document).find("meta[name='_csrf']").attr("content");
//	var header = angular.element(document).find("meta[name='_csrf_header']").attr("content");
	
//	console.log(token);
//	console.log(header);
	
	
	$httpProvider.defaults.headers.common[header] = token;
	//$httpProvider.interceptors.push('defaultErrorInterceptor');
	
	
	
	$locationProvider.html5Mode({
		enabled: true,
//			requireBase: false
	});
	
	
	$mdThemingProvider.theme('default');
	
	
//	$mdThemingProvider.definePalette('amazingPaletteName', {
//	    '50': 'ffebee',
//	    '100': 'ffcdd2',
//	    '200': 'ef9a9a',
//	    '300': 'e57373',
//	    '400': 'ef5350',
//	    '500': 'f44336',
//	    '600': 'e53935',
//	    '700': 'd32f2f',
//	    '800': 'c62828',
//	    '900': 'b71c1c',
//	    'A100': 'ff8a80',
//	    'A200': 'ff5252',
//	    'A400': 'ff1744',
//	    'A700': 'd50000',
//	    'contrastDefaultColor': 'light',    // whether, by default, text (contrast)
//	                                        // on this palette should be dark or light
//	    'contrastDarkColors': ['50', '100', //hues which contrast should be 'dark' by default
//	     '200', '300', '400', 'A100'],
//	    'contrastLightColors': undefined    // could also specify this if default was 'dark'
//	  });
//	  $mdThemingProvider.theme('default')
//	    .primaryPalette('amazingPaletteName')
});
