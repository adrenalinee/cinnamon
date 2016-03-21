angular.module('cinnamon')
.controller('configuration.sites.modify', function($scope, $http, $state, $stateParams, $mdDialog, $mdToast, $interval) {
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
			$mdToast.show(
					$mdToast.simple()
						.textContent('입력값을 확인해 주세요.')
						.position('top right')
						.hideDelay(3000)
			);
			return ;
		}
		
		var params = angular.copy($scope.site);
		console.log(params);
		$http.put('/rest/configuration/sites/' + $stateParams.siteId, params)
		.success(function(result, status, headers) {
			$mdToast.show(
				$mdToast.simple()
					.textContent("수정되었습니다.")
					.position("top right")
					.hideDelay(3000)
			)
			
			$interval(function() {
				$state.go('view',{siteId : $scope.site.siteId})
			}, 200, 1);
		})
	}
	
	$scope.goView = function() {
		$state.go('view', {siteId : $stateParams.siteId})
	}
});