package com.commandrunner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.commandrunner.bean.Command;
import com.commandrunner.bean.Group;
import com.commandrunner.bean.Result;
import com.commandrunner.component.CommandRunner;
import com.commandrunner.service.ConfigurationService;

@RestController
@RequestMapping("/command")
public class CommandController {

	private static final Logger logger = LoggerFactory.getLogger(CommandController.class);

	@Autowired
	private CommandRunner commandRunner;

	@Autowired
	private ConfigurationService configurationService;

	@RequestMapping(value = "/{idCommand}", method = RequestMethod.GET)
	public Result run(@PathVariable("idCommand") Long idCommand) {
		logger.info("idCommand received [ " + idCommand + " ].");
		Command command = this.configurationService.getConfiguration(idCommand);
		return this.commandRunner.run(this.getCommandFormated(command), configurationService.getDirectoryCommand());
	}

	private String getCommandFormated(Command command) {
		return "./" + command.getScript();
	}

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public List<Group> getGroups() {
		logger.info("retrieving groups.");
		return this.configurationService.getConfiguration().getGroups();
	}

}
