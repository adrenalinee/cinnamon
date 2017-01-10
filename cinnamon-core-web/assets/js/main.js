angular.module('cinnamon.core')
.config(function($httpProvider) {
	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
});