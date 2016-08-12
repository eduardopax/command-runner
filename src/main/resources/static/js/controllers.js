'use strict';

scApp.controller('CommandController', [ '$scope', 'CommandService',
		function($scope, CommandService) {
			var self = this;
			
			self.on = function(index) {
				CommandService.on(index).then(function(data) {
					$('#status' + index).text(data);
				}, function(errResponse) {
					console.error('Error');
				});
			};
			
			self.off = function(index) {
				CommandService.off(index).then(function(data) {
					$('#status' + index).text(data);
				}, function(errResponse) {
					console.error('Error');
				});
			};
			
			self.execute = function(index) {
				CommandService.execute(index).then(function(data) {
					$('#status' + index).text(data);
				}, function(errResponse) {
					console.error('Error');
				});
			};

			self.status = function(index) {
				CommandService.status(index).then(function(data) {
					$('#status' + index).text(data);
				}, function(errResponse) {
					console.error('Error');
				});
			};

		} ]);
