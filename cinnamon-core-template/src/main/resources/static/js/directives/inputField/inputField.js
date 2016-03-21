(function() {
	'use strict'

	angular.module('cinnamon')
	.directive('inputField', function() {
		return {
			restrict: 'E',
			templateUrl: '/directives/inputField',
			require : 'ngModel',
			replace: true,
			scope: {
				label: '=',
				type: '=',
				name: '=',
				required: '=',
				maxlength: '='
			}
			
			
		}
	});
})();