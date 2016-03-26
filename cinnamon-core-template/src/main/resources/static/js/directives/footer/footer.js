(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('footer', function() {
		console.log('footer');
		
		return {
			restrict: 'E',
			templateUrl: '/js/directives/footer/footer.tpl.html',
			controller: controller,
			link: link
		}
	});
	
	function controller($scope, $http) {
		console.log('footerController');
		
	}
	
	
	function link(scope, element, attr) {
		element.addClass('main-footer');
	}
})();