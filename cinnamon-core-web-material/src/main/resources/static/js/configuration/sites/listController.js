angular.module('cinnamon')
.controller('configuration.sites.list', function($scope, $http, $interval, $state) {
	console.log('configuration.sites.list');
	
	$scope.domains;
	
	
	$scope.goView = function(site) {
		$interval(function() {
			$state.go('view', {siteId: site.siteId});
		}, 200, 1);
	}
});