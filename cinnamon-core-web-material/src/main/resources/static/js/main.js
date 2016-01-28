'use strict'

angular.module('cinnamon', [
//		'ngResource',
	'ui.router',
	'ngMaterial'
]).config(function($locationProvider) {
	$locationProvider.html5Mode({
		enabled: true,
//			requireBase: false
	});
	
});
