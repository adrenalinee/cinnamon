angular.module('cinnamon')
.directive('cmDialogTemplate', function() {
	return {
		restrict: 'EA',
		transclude: true,
		scope: {
			toolbarTitle: '=',
			actionName: '=?',
			onAction: '&?',
			form: '@'
		},
		templateUrl: '/configuration/directives/dialogTemplate',
		controller: 'dialogTemplateController'
	}
}).controller('dialogTemplateController', function($scope, $http, $mdDialog, $mdMedia) {
	console.log('dialogTemplateController');
	
	console.log($scope.form);
	
	
	$scope.close = function() {
		$mdDialog.hide();
	}
	
	$scope.isMobile = function() {
		return $mdMedia('xs');
	}
	
	$scope.action = function(params) {
		console.log('action');
		console.log(params);
		
		$scope.onAction();
	}
});