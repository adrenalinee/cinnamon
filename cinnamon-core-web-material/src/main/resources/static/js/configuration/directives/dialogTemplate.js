angular.module('cinnamon')
.directive('cmDialogTemplate', function() {
	return {
		restrict: 'EA',
		transclude: true,
		scope: {
			toolbarTitle: '=',
			actionName: '=?',
			onAction: '&?'
		},
		templateUrl: '/configuration/directives/dialogTemplate',
		controller: 'dialogTemplateController'
	}
}).controller('dialogTemplateController', function($scope, $http, $mdDialog, $mdMedia) {
	console.log('dialogTemplateController');
	
	
	
	$scope.close = function() {
		$mdDialog.hide();
	}
	
	$scope.isMobile = function() {
		return $mdMedia('xs');
	}
});