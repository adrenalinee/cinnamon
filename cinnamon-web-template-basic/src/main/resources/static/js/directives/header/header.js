(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('header', function() {
		console.log('header');
		
		return {
			restrict: 'E',
			templateUrl: '/js/directives/header/header.tpl.html',
			controller: headerController,
			link: link
		}
	});
	
	function headerController($scope, $http) {
		console.log('headerController');
		
	}
	
	
	function link(scope, element, attr) {
		element.addClass('main-header');
	}
})();