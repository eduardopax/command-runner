package com.commandrunner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.commandrunner.bean.Command;
import com.commandrunner.rest.bean.Result;
import com.commandrunner.runner.CommandRunner;

@RestController
@RequestMapping("/command")
public class CommandController {

	private static final Logger logger = LoggerFactory.getLogger(CommandController.class);

	@Autowired
	private CommandRunner commandRunner;

	@Autowired
	private ConfigController configController;

	@RequestMapping(value = "/{idCommand}", method = RequestMethod.GET)
	public Result run(@PathVariable("idCommand") Long idCommand) {
		logger.info("idCommand received [ " + idCommand + "]");
		Command command = this.configController.getConfig(idCommand);
		return this.commandRunner.run(command.getScript());
	}

}
