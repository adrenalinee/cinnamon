(function() {
	'use strict'
	
	angular.module('cinnamon', [
//		'ui.router'
	]).config(function($locationProvider) {
		$locationProvider.html5Mode(true);
		
	});
})();