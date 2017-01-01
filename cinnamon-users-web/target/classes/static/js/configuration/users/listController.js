angular.module('cinnamon')
.controller('configuration.users.list', function($scope, $http, $interval, $state, $location, $mdDialog, $mdMedia) {
	console.log('configuration.users.list');
	
	$scope.goView = function(user) {
		$interval(function() {
			$state.go('view', {userId: user.userId});
		}, 150, 1);
	}

});