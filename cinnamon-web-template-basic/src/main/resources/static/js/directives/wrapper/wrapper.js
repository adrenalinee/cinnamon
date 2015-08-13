(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('wrapper', function() {
		console.log('wrapper');
		
		return {
			restrict: 'A',
			templateUrl: '/js/directives/wrapper/wrapper.tpl.html',
			controller: controller,
			link: link
		}
	});
	
	function controller($scope, $http) {
		console.log('wrapperController');
		
	}
	
	function link(scope, element, attr) {
		element.addClass('wrapper');
		
	}
})();