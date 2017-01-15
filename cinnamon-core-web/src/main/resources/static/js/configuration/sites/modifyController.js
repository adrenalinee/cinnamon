angular.module('cinnamon')
.controller('configuration.sites.modify', function($scope, $http, $state, $stateParams, $mdDialog, $mdToast, $interval, message, pageMove) {
	console.log('configuration.sites.modify');

	$scope.site;
	
	$scope.load = function() {
		$http.get('/rest/configuration/sites/' + $stateParams.siteId)
		.success(function(result) {
			$scope.site = result;
		})
	}
	// 정보 불러오기
	$scope.load();
	
	// 수정
	$scope.modify = function() {
		
		if(!$scope.frm.$valid) {
			message.alert('입력값을 확인해 주세요.');
			return ;
		}
		
		var params = angular.copy($scope.site);
		console.log(params);
		$http.put('/rest/configuration/sites/' + $stateParams.siteId, params)
		.success(function(result, status, headers) {
			message.alert('수정되었습니다. 라벨 변경은 페이지 새로고침(F5)해야 적용됩니다.');
			pageMove.go('view',{siteId : $scope.site.siteId});
		})
	}
	
	$scope.goBack = function() {
		history.go(-1);
	}
});