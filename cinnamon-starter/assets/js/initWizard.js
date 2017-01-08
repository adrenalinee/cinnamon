var angular = require('angular');
require('@cinnamon/core-web');
angular.module('cinnamon.starter', ['cinnamon.core'])
.config(function($locationProvider, $mdThemingProvider) {
	console.log('!!!');
	$locationProvider.html5Mode(true);
	$mdThemingProvider.theme('default');
});