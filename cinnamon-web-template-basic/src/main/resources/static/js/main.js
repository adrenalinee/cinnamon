(function() {
	'use strict'
	
	angular.module('cinnamon', [
		'ui.router',
		'ui.bootstrap'
	]).config(function($locationProvider) {
		$locationProvider.html5Mode(true);
		
	});
})();