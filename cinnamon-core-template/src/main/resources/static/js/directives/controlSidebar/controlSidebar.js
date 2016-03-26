(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('controlSidebar', function() {
		console.log('controlSidebar');
		
		return {
			restrict: 'E',
			templateUrl: '/js/directives/controlSidebar/controlSidebar.tpl.html',
			controller: controller,
			link: link
		}
	});
	
	function controller($scope, $http) {
		console.log('controlSidebar');
		
	}
	
	
	function link(scope, element, attr) {
		element.addClass('control-sidebar');
		element.addClass('control-sidebar-dark');
		element.after('<div class="control-sidebar-bg"></div>');
	}
})();