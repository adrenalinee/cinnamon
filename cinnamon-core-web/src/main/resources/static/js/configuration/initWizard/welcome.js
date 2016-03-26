angular.module('cinnamon')
.controller('welcomeController', function($scope, $state) {
	$scope.next = function() {
		$state.go('baseData');
	}
});