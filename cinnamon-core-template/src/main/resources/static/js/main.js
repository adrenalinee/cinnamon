(function() {
	'use strict'
	
	angular.module('cinnamon', [
//		'ngResource',
		'ngAnimate',
		'ngAria',
		'ngMaterial',
		'ui.router',
		'ui.bootstrap',
		'ngBootbox',
		'toastr',
	]).config(function($locationProvider, toastrConfig) {
		$locationProvider.html5Mode({
			enabled: true,
//			requireBase: false
		});
		
		angular.extend(toastrConfig, {
			closeButton: true,
			progressBar: true
		});
	});
})();