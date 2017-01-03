require('@cinnamon/core-web');
angular.module('cinnamon.starter', ['cinnamon.core', 'ngComponentRouter'])
.value('$routerRootComponent', 'appMain')
.config(function($locationProvider) {
	$locationProvider.html5Mode(true);
});