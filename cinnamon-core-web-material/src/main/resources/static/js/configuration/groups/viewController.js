angular.module('cinnamon')
.controller('configuration.group.view', function($scope, $http, $interval, $state, $stateParams, $log, $mdToast, $mdDialog, $mdMedia) {
	console.log('configuration.group.view');
	// 화면 도메인
	$scope.domains;
	
	// 기본 정보
	$scope.load = function() {
		$http.get('/rest/configuration/groups/' + $stateParams.groupId)
			.success(function(result) {
				console.log(result);
				$scope.domains = result;
			})
	}
	$scope.load();
});