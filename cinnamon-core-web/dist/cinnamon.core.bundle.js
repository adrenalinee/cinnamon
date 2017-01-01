angular.module('cinnamon.core', [
	'mdSteppers',
	'ngComponentRouter',
	'ngMessages',
	'ngAnimate',
	'ngAria',
	'ngMaterial']);

angular.module('cinnamon.core')
.component('appMain', {
	templateUrl: 'core/components/appMain',
	$routeConfig: [
		{path: '/initWizard', name: 'InitWizard', component: 'initWizard'}
	],
	controller: appMainController
});

function appMainController() {
	
}
angular.module('cinnamon.core')
.component('initWizard', {
	templateUrl: 'core/components/initWizard',
	controller: InitWizardController
});

function InitWizardController() {
	
}