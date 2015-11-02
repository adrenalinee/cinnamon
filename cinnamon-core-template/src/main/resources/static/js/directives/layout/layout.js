(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('layout', function($window, $document, $timeout) {
		console.log('layout');
		
		return {
			restrict: 'E',
			transclude: true,
			scope: {},
//			templateUrl: '/js/directives/layout/layout.tpl.html',
			template: '<ng-transclude />',
			controller: function($scope, $document, $http) {
				
				//TODO dimension을 외부에서 받아야 한다.
				$http.get('/rest/configuration/menu-groups/configuration/current-menus')
				.success(function(data) {
					console.log(data);
					$scope.menus = data;
				});
				
				
				this.getSidebarMenus = function() {
					return $scope.menus.sidebar;
				}
				
				
				//
				this.addContent = function(content) {
					$scope.content = content;
				}
				
				//
				this.toggleSidebar = function() {
					console.log('toggleSidebar');
					$document.find('body').toggleClass('sidebar-collapse');
				}
				
				//
				this.toggleControlSidebar = function() {
					console.log('toggleSidebar');
					$document.find('control-sidebar').toggleClass('control-sidebar-open');
				}
			},
			link: function(scope, element, attr) {
				element.addClass('wrapper');
				
				//
				angular.element($window).on('resize', function(event) {
					console.log('resize');
					resizeContentMinHeight($window, element, scope.content);
				});
				
				//수정해야함.. header, footer가 로딩된 다음에 전달 받을 수 있는 이벤트를 찾아야 함
				$timeout(function() {
					resizeContentMinHeight($window, element, scope.content);
				}, 10);
			}
		}
	}).directive('content', function() {
		console.log('content');
		
		return {
			restrict: 'E',
			transclude: true,
			require: '^layout',
			replace: true,
			template: '<div class="content-wrapper" ng-transclude></div>',
			link: function(scope, element, attrs, layoutController) {
				layoutController.addContent(element);
			}
		};
	});
	
	//
	function resizeContentMinHeight($window, layout, content) {
//		var heasers = layout.find('header');
//		var footsers = layout.find('footer');
//		
//		var headerAndFooterHeight = 0;
//		if (heasers.length > 0) {
//			headerAndFooterHeight = heasers[0].clientHeight;
//		}
//		if (footsers.length > 0) {
//			headerAndFooterHeight += footsers[0].clientHeight;
//		}
		
		var headerAndFooterHeight = layout.find('header')[0].clientHeight + layout.find('footer')[0].clientHeight;
		var windowHeight = $window.innerHeight;
		var sidebarHeight = $window.innerHeight;
		
		var sidebars = layout.find('sidebar');
		if (sidebars.length > 0) {
			sidebarHeight = sidebars[0].clientHeight;
		}
		
//		console.log(headerAndFooterHeight);
//		console.log(windowHeight);
//		console.log(sidebarHeight);
		
		var postSetWidth;
		if (windowHeight >= sidebarHeight) {
//			console.log(content);
			
			content.css('min-height', (windowHeight - headerAndFooterHeight - 1) + 'px');
		} else {
			content.css('min-height', sidebarHeight + 'px');
		}
		
		
	}
})();