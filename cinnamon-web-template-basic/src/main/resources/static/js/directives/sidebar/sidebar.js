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
		
		var params = {
			position: 'sidebar'
		};
		$http.get('/rest/menus', {
			params: params
		}).success(function(data) {
			$scope.sideMenus = data._embedded.menus;
		});
	}
	
	
	function link(scope, element, attr) {
		element.addClass('main-sidebar');
	}
})();