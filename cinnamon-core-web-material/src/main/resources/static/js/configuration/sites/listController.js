angular.module('cinnamon')
.controller('configuration.sites.list', function($scope, $http, $interval, $state, pageMove) {
	console.log('configuration.sites.list');
	
	$scope.domains;
	$scope.searchInfo = {};
	
	$scope.goView = function(site) {
		pageMove.go('view', {siteId: site.siteId});
	}
});