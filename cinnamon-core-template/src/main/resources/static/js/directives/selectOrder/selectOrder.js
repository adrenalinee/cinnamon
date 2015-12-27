(function() {
	'use strict'
	
	angular.module('cinnamon')
	.directive('selectOrder', function() {
		return {
			restrict: 'E',
			templateUrl: '/directives/selectOrder',
			scope: {
				orderFields: '=',
				onChanged: '&'
			},
			replace: true,
			controller: function($scope) {
				console.log($scope.orderFields);
				
				var orderFields = $scope.orderFields;
				$scope.items = [];
				for (var field in orderFields) {
					$scope.items.push({
						name: orderFields[field],
						value: field,
						direction: ''
					});
				}
				
				
				$scope.directionClass = function(field) {
					if (field.direction == 'desc') {
						return 'fa fa-arrow-down';
					} else if (field.direction == 'asc') {
						return 'fa fa-arrow-up';
					} else {
						return '';
					}
				}
				
				$scope.changeOder = function(item) {
					item.direction = nextDirection(item.direction);
					$scope.onChanged({
						field: item.name,
						direction: item.direction
					});
				}
			}
		}
	});
	
	function nextDirection(direction) {
		if (direction == 'asc') {
			return 'desc';
		} if (direction == 'desc') {
			return 'asc';
		}
	}
})();