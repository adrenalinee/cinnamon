angular.module('cinnamon')
.directive('cmFileUpload', function() {
	return {
		restrict: 'E',
		scope: {
			fileModel: '='
		},
		templateUrl: '/configuration/directives/fileUpload',
		controller: 'fileUploadontroller'
	}
}).controller('fileUploadontroller', function($scope, $http) {
	console.log('fileUploadontroller');
	
	
	
});