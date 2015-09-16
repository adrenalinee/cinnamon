(function() {
	'use strict'
	
	angular.module('cinnamon', [
//		'ngResource',
		'ngAnimate',
		'ui.router',
		'ui.bootstrap',
		'ngBootbox',
		'toastr'
	]).config(function($locationProvider) {
		$locationProvider.html5Mode({
			enabled: true,
			requireBase: false
		});
		
	});
})();