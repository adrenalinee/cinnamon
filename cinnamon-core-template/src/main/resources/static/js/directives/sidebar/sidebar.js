(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('sidebar', function() {
		console.log('sidebar');
		
		return {
			restrict: 'E',
			require: '^layout',
			templateUrl: '/partials/sidebar',
//			scope: {
//				menus: '='
//			},
//			replace: true,
			controller: controller,
			link: link
		}
	});
	
	function controller($scope, $http) {
		//TODO dimension을 외부에서 받아야 한다.
//		$http.get('/rest/layout/configuration/sidebar')
//		.success(function(data) {
//			console.log(data);
//			$scope.menus = data;
//		});
		
		$scope.toggleShowChild = function(menu) {
			console.log('toggleShowChild');
			
			if (menu.showChild == undefined) {
				menu.showChild = true;
			} else {
				menu.showChild = !menu.showChild;
			}
		}
		
		
		$scope.menuLiClass = function(menu) {
			var classes = '';
			if (menu.childs.length > 0) {
				classes += "treeview";
			}
			if (menu.showChild) {
				classes += " active";
			}
			
			return classes;
		}
	}
	
	
	function link(scope, element, attr, layoutController) {
		console.log('sidebar link');
		
		element.addClass('main-sidebar');
//		scope.menus = layoutController.getSidebarMenus();
	}
})();