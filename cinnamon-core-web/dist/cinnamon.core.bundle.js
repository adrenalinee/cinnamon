angular.module('cinnamon.core')
.config(function($httpProvider) {
	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
	$httpProvider.interceptors.push(function($q, $injector) {
		
		return {
			'request': function(config) {
				if (config.method == 'GET')
				config.data = '';
				
				return config;
			},
			'responseError': function(rejection) {
				console.log('responseError!');
				
				var $mdDialog = $injector.get('$mdDialog');
				
				if (rejection.status == 400) {
					if (rejection.data != null) {
						if (rejection.data.violations != null) {
							var message = '';
							
							rejection.data.violations.forEach(function(v) {
								message += '<b>' + v.paramName + '</b> :';
								message += v.message + '<br />';
							});
							
							$mdDialog.show(
								$mdDialog.alert()
									.title('입력 오류')
									.htmlContent(message)
									.ok('확인'));
						}
					}
				} else if (rejection.status == 401) {
					$mdDialog.show(
						$mdDialog.alert()
							.title('인증 오류')
							.htmlContent('로그인해주시기 바랍니다.')
							.ok('확인'));
				} else if (rejection.status == 403) {
					$mdDialog.show(
						$mdDialog.alert()
							.title('숨겨진 자원 접근 오류')
							.htmlContent('권한이 없습니다.')
							.ok('확인'));
				} else if (rejection.status == 500) {
					$mdDialog.show(
						$mdDialog.alert()
							.title('서버 오류')
							.htmlContent(rejection.data)
							.ok('확인'));
				}
				
				
				return $q.reject(rejection);
			}
		}
	});
});
angular.module('cinnamon.core')
.component('appMain', {
	templateUrl: 'core/components/appMain',
	controller: appMainController
});

function appMainController() {
	
}
angular.module('cinnamon.core')
.component('initWizard', {
	templateUrl: 'core/components/initWizard',
	controller: InitWizardController
});

function InitWizardController($scope, $http) {
	
	$http.get('/initWizard')
	.success(function(data) {
		console.log(data);
		$scope.wizard = data;
	});
}