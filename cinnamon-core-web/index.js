var angular = require('angular');
require('angular-animate');
require('angular-aria');
require('angular-messages');
require('angular-sanitize');

require('angular-ui-router');

require('angular-material');
require('angular-material/angular-material.css');

require('material-steppers');
require('material-steppers/dist/material-steppers.css');

angular.module('cinnamon.core', [
	'ngAnimate',
	'ngAria',
	'ngMessages',
	'ngMaterial',
	'mdSteppers',
	'ngSanitize', 
	'ui.router']);

require('./dist/cinnamon.core.bundle');
require('./dist/cinnamon.core.css');

