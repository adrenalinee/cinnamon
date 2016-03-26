angular.module('cinnamon')
.controller('configuration.sites.create', function($scope, $http, $stateParams, $mdDialog, $mdToast, $interval) {
	console.log('configuration.sites.create');

	// 생성
	$scope.create = function() {
		
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
		$http.post('/rest/configuration/sites', params)
		.success(function(result, status, headers) {
			$mdToast.show(
				$mdToast.simple()
					.textContent("등록되었습니다.")
					.position("top right")
					.hideDelay(3000)
			)
			
			$interval(function() {
				var viewLink = headers("Location");
				location.href = viewLink;
			}, 200, 1);
		})
		.error(function(error) {
			$mdToast.show(
					$mdToast.simple()
					.textContent("site 중복입니다.")
					.position('top right')
					.hideDelay(3000)
			)
		})
	}
});