(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('sidebar', function() {
		console.log('sidebar');
		
		return {
			restrict: 'E',
			templateUrl: '/js/directives/sidebar/sidebar.tpl.html',
			scope: {
				menus: '='
			},
//			replace: true,
			controller: controller,
			link: link
		}
	});
	
	function controller($scope, $http) {
		console.log('sidebarController');
		
		
		this.aa = function() {
			console.log('aa');
		}
	}
	
	
	function link(scope, element, attr) {
		element.addClass('main-sidebar');
	}
})();