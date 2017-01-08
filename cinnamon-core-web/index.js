var angular = require('angular');
require('@angular/router/angular1/angular_1_router');
require('angular-animate');
require('angular-aria');
require('angular-material');
require('angular-material/angular-material.css');
require('angular-messages');
require('material-steppers');
require('material-steppers/dist/material-steppers.css');
//require('angular-ui-router');

angular.module('cinnamon.core', [
	'mdSteppers',
	'ngMessages',
	'ngAnimate',
	'ngAria',
	'ngComponentRouter',
	'ngMaterial',
	'ui.router']);

require('./dist/cinnamon.core.bundle');
require('./dist/cinnamon.core.css');

