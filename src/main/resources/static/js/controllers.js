'use strict';

commandRunnerApp.controller('CommandController', [ '$scope', 'CommandService',
		function($scope, CommandService) {
			var self = this;
			self.groups=[];

			self.execute = function(commandGroupId, index) {
				CommandService.execute(index).then(function(data) {
					$('#status' + commandGroupId).text(data.resultEnum);
				}, function(errResponse) {
					console.error('Error while run command : ' + index);
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

		} ]);
