(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('header', function() {
		console.log('header');
		
		return {
			restrict: 'E',
			require: '^layout',
			templateUrl: '/js/directives/header/header.tpl.html',
			controller: headerController,
			link: link
		}
	});
	
	function headerController($scope, $http) {
		console.log('headerController');
	}
	
	
	function link(scope, element, attr, layoutController) {
		element.addClass('main-header');
		
		//
		scope.toggleSidebar = function() {
			layoutController.toggleSidebar();
		}
		
		//
		scope.toggleControlSidebar = function() {
			layoutController.toggleControlSidebar();
		}
	}
})();