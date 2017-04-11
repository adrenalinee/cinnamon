angular.module('cinnamon')
.component('cmList', {
	templateUrl: '/core/components/common/cmList',
	controller: ListController,
	transclude: {
		'items': 'cmListItems',
		'filters': 'cmListFilters',
	},
	bindings: {
		resourceUrl: '@',
		rowInfo: '=',
		searchInfo:  '=?'
	}
});

function ListController($scope, $http) {
	var ctrl = this;
	
	ctrl.$onInit = function() {
		ctrl.searchInfo = {};
	}
	
	ctrl.onSearch = function(event) {
		if (event.keyCode == 13) {
			ctrl.paginatorCallback(1, 20);
		}
	}
	
	ctrl.paginatorCallback = function(page, pageSize) {
		console.log('paginatorCallback ' + page + ", " + pageSize);
		
		
		
		var params = angular.copy(ctrl.searchInfo);
		params.page = page - 1;
		params.size = pageSize;
		
		return $http.get(ctrl.resourceUrl, {params: params})
		.then(function(result) {
			console.log(result);
			return {
				results: result.data.content,
				totalResultCount: result.data.totalElements
			}
		});
	}

}