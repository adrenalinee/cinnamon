var angular = require('angular');
require('angular-animate');
require('angular-aria');
require('angular-material');
require('angular-material/angular-material.css');
require('angular-messages');
require('material-steppers');
require('material-steppers/dist/material-steppers.css');

angular.module('cinnamon.core', [
	'mdSteppers',
	'ngComponentRouter',
	'ngMessages',
	'ngAnimate',
	'ngAria',
	'ngMaterial']);

require('./dist/cinnamon.core.bundle');
require('./dist/cinnamon.core.css');

