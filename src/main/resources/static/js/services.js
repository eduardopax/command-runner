'use strict';

scApp.factory('CommandService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				on : function(index) {
					return $http.get(path + '/command/on/' + index).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								return $q.reject(errResponse);
							});
				},
				off : function(index) {
					return $http.get(path + '/command/off/' + index).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								return $q.reject(errResponse);
							});
				},
				execute : function(index) {
					return $http.get(path + '/command/execute/' + index).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								return $q.reject(errResponse);
							});
				},
				status : function(index) {
					return $http.get(path + '/command/status/' + index).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								return $q.reject(errResponse);
							});
				}

			};

		} ]);
