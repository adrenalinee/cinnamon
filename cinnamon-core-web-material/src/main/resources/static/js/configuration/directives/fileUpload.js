angular.module('cinnamon')
.directive('cmFileUpload', function() {
	return {
		restrict: 'E',
		scope: {
			fileModel: '=',
			upload: '&'
		},
		templateUrl: '/configuration/directives/fileUpload',
		controller: 'fileUploadontroller'
	}
}).controller('fileUploadontroller', function($scope, $http) {
	console.log('fileUploadontroller');
	
	
	
});