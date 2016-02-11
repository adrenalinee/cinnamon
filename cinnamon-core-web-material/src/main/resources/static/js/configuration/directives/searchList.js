angular.module('cinnamon')
.directive('cmSearchList', function() {
	return {
		restrict: 'E',
		transclude: true,
		scope: {
			resourceUrl: '=',
			onSelect: '&',
//			domain: '='
		},
		templateUrl: '/configuration/directives/searchList',
		link: function(scope, element, attrs, controller, transclude) {
			scope.$watch('domains', function() {
				if (scope.domains == null) {
					return;
				}
				
//				var parent = element.parent();
			
				var domains = scope.domains;
				for (var i = 0; i < domains.content.length; i++) {
					var childScope = scope.$new();
					var domain = domains.content[i];
					
					console.log(domain);
					
					childScope['domain'] = domain;
					transclude(childScope, function(clone) {
						console.log(clone);
						
					});
				}
			});
		},
//		compile: function(element, attrs,transcludeFn) {
//			return function($scope, $element, $attr) {
//				console.log('compile');
//				
//				$scope.$watch('domains', function() {
//					if ($scope.domains == null) {
//						return;
//					}
//					
//					var parent = $element.parent();
////					parent.children().remove();
//					
//					var domains = $scope.domains;
//					for (var i = 0; i < domains.content.length; i++) {
//						var childScope = $scope.$new();
//						var domain = domains.content[i];
//						childScope['domain'] = domain;
//						transcludeFn(childScope, function(clone) {
//							parent.append(clone);
//						});
//					}
//				});
//			}
//			
//		},
		controller: 'searchListController'
	}
}).directive('cmSearchListTransclude', function() {
	return {
		template: '<ng-transclude />'
		link: function(scope, element, attrs, controller, transclude) {
			scope.$watch('domains', function() {
				if (scope.domains == null) {
					return;
				}
				
//				var parent = element.parent();
			
				var domains = scope.domains;
				for (var i = 0; i < domains.content.length; i++) {
					var childScope = scope.$new();
					var domain = domains.content[i];
					
					console.log(domain);
					
					childScope['domain'] = domain;
					transclude(childScope, function(clone) {
						console.log(clone);
					});
				}
			});
		}
	}
}).controller('searchListController', function($scope, $http) {
	console.log('searchListController');
	
	$scope.domains;
	$scope.searchInfo = {
		sort: 'createdAt,desc'
	};
	
	$scope.showDetailSearch;
	
	
	$scope.load = function(params) {
		console.log($scope.resourceUrl);
		
		$http.get($scope.resourceUrl, {params: params})
		.success(function(data) {
			console.log(data);
			
			$scope.domains = data;
		});
	}
	
	$scope.onPageChange = function() {
		console.log('onPageChange');
		$scope.search();
	}
	
	$scope.search = function() {
		console.info('search');
		
		var params = angular.copy($scope.searchInfo);
		for(param in params) {
			if (param == 'page') {
				params[param]--;
			}
		}
		
//		$location.search($scope.searchInfo);
		
		$scope.load(params);
	}
	
	$scope.load($scope.searchInfo);
});