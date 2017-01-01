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