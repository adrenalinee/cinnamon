angular.module('cinnamon')
.directive('users', function() {
	return {
		restrict: 'E',
		scope: {
			onSelect: '&'
		},
		templateUrl: '/configuration/directives/users',
		controller: function($scope, $http) {
			
			load($scope, $http);
		}
	}
});

var load = function($scope, $http) {
	$http.get('/rest/configuration/users')
	.success(function(data) {
		console.log(data);
		
		$scope.domains = data;
	});
}