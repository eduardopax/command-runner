'use strict';

commandRunnerApp.controller('CommandController', [ '$scope', 'CommandService',
		function($scope, CommandService) {
			var self = this;
			
			self.execute = function(index) {
				CommandService.execute(index).then(function(data) {
					$('#status' + index).text(data.resultEnum);
				}, function(errResponse) {
					console.error('Error');
				});
			};

		} ]);
