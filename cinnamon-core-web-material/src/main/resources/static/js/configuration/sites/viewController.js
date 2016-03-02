angular.module('cinnamon')
.controller('configuration.sites.view', function($scope, $http, $stateParams, $mdDialog) {
	console.log('configuration.sites.view');
	
	var siteId = $stateParams.siteId;
	$scope.siteId = siteId;
	
	$http.get('/rest/configuration/sites/' + siteId)
	.success(function(data) {
		console.log(data);
		
		$scope.domain = data;
	});
	
	
	$scope.goMenuGroupView = function(domain) {
		location.href = '/configuration/menuGroups/' + domain.menuGroupId;
	}
});