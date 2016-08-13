'use strict';

commandRunnerApp.factory('CommandService', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				execute : function(index) {
					return $http.get(path + '/command/' + index).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								return $q.reject(errResponse);
							});
				}

			};

		} ]);
