/**
 * 
 */
angular.module('cinnamon')
.factory('groupService', function($http) {
	return {
		getGroupMap : function(groupId, callback) {
			$http.get('/rest/configuration/groups/' + groupId + '/childsMap')
				.success(function(result) {
					console.log(result);
					callback(result);
				})
		},
		getGroups : function(groupId, callback) {
			$http.get('/rest/configuration/groups/' + groupId + '/childs')
			.success(function(result) {
				console.log(result);
				callback(result);
			})
		}
	}
})
// 단순 얼럿 처리
.factory('message', function($mdToast, $mdDialog) {
	return {
			alert: function(text) {
				$mdToast.show(
					$mdToast.simple()
					.textContent(text)
					.position('right top')
					.hideDelay(3000)
				)
			},
			confirm : function(title, cotents, callback) {
				var confirm = $mdDialog.confirm()
				.title(title)
				.textContent(cotents)
				.ok('예')
				.cancel('아니오');
				$mdDialog.show(confirm).then(function() {
					callback();
				} ,function() {
				})
			}
	}
})
// 페이지 이동
.factory('pageMove', function($interval, $state) {
	return {
		go : function() {
			var arg = arguments;
			$interval(function() {
				if(arg.length == 1) {
					$state.go(arg[0]);	
				}else if(arg.length == 2){
					$state.go(arg[0], arg[1]);
				}
			}, 150, 1);
		},
		location : function(url) {
			$interval(function() {
				location.href = url;
			}, 150, 1)
		}
	}
})
