angular.module('cinnamon')
.component('cmList', {
	templateUrl: '/core/components/cmList',
	controller: ListController,
	transclude: {
		'items': 'cmListItems',
		'filters': 'cmListFilters',
	},
	bindings: {
		resourceUrl: '@',
		rowInfo: '='
	}
});

function ListController($scope, $http) {
	var ctrl = this;
	
	ctrl.$onInit = function() {

	}
	
	ctrl.paginatorCallback = function(page, pageSize) {
		console.log('paginatorCallback');
		
		var offset = (page-1) * pageSize;
		
		return $http.get(ctrl.resourceUrl)
		.then(function(result) {
			console.log(result);
			return {
				results: result.data.content,
				totalResultCount: result.data.totalElements
			}
		});
	}
}