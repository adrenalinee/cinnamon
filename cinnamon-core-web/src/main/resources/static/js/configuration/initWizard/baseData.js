angular.module('cinnamon')
	.controller('baseDataController', function($scope, $http, $state) {
		
		//
		$scope.createBaseData = function() {
			$http.post('/rest/configuration/initWizard/baseData')
				.success(function(data) {
					
					$state.go('firstUser');
				});
			
		}
	});