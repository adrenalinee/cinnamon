angular.module('cinnamon')
.controller('configuration.sites.create', function($scope, $http, $stateParams, $mdDialog, $mdToast, $interval, message) {
	console.log('configuration.sites.create');

	// 생성
	$scope.create = function($event) {
		
		if (!$scope.frm.$valid) {
			message.alert('입력값을 확인해 주세요.');
			return;
		}
		
		var params = angular.copy($scope.site);
		console.log(params);
		$http.post('/rest/configuration/sites', params)
		.success(function(result, status, headers) {
			
			message.alert('등록되었습니다.');
			
			var viewLink = headers("Location");
			location.replace(viewLink);
			
		});
	}
});