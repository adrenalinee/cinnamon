angular.module('cinnamon')
.controller('baseDataController', function($scope, $state) {
	$scope.createBaseData = function() {
		$state.go('firstUser');
	}
});