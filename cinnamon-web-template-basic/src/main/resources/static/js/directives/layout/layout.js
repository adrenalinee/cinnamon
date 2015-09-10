(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('layout', function($window, $document, $timeout) {
		console.log('layout');
		
		return {
			restrict: 'E',
			transclude: true,
			scope: {},
			templateUrl: '/js/directives/layout/layout.tpl.html',
			controller: function($scope, $document) {
				
				//
				this.addContent = function(content) {
					$scope.content = content;
				}
				
				//
				this.toggleSidebar = function() {
					console.log('toggleSidebar');
					
					$document.find('body').toggleClass('sidebar-collapse');
				}
			},
			link: function(scope, element, attr) {
				element.addClass('wrapper');
				
				//
				angular.element($window).on('resize', function(event) {
					console.log('resize');
					resizeContentMinHeight($window, element, scope.content);
				});
				
				//
//				$document.ready(function(event) {
//					console.log('ready');
//					resizeContentMinHeight($window, element, scope.content);
//				});
				
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
		var headerAndFooterHeight = layout.find('header')[0].clientHeight + layout.find('footer')[0].clientHeight;
		var windowHeight = $window.innerHeight;
		var sidebarHeight = layout.find('sidebar')[0].clientHeight;
		
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