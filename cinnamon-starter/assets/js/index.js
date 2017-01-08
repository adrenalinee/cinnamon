var angular = require('angular');
require('@cinnamon/core-web');
angular.module('cinnamon.starter', ['cinnamon.core'])
.config(function($locationProvider) {
	$locationProvider.html5Mode(true);
});