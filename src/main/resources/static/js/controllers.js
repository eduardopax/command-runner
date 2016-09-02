'use strict';

commandRunnerApp.controller('CommandController', [ '$scope', 'CommandService',
		'blockUI', function($scope, CommandService, blockUI) {
			// Block the user interface
			blockUI.start();

			var self = this;
			self.groups = [];
			self.execute = function(commandGroupId, index) {
				blockUI.start("Executing...");
				CommandService.execute(index).then(function(data) {
					if (data.message != null) {
						$('#status' + commandGroupId).text(data.message);
					} else {
						$('#status' + commandGroupId).text(data.resultEnum);
					}
					blockUI.stop();
				}, function(errResponse) {
					console.error('Error while run command : ' + index);
					blockUI.stop();
				});
			};

			self.fetchAllGroups = function() {
				CommandService.fetchAllGroups().then(function(groupsData) {
					self.groups = groupsData;
					console.log(groupsData)
				}, function(errResponse) {
					console.error('Error while fetching Groups');
				});
			};

			self.fetchAllGroups();

			blockUI.stop();
		} ]);
