package com.commandrunner.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import com.commandrunner.bean.Command;
import com.commandrunner.bean.CommandGroup;
import com.commandrunner.bean.Config;
import com.commandrunner.bean.Group;

@Controller
public class ConfigController {

	private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

	@Autowired
	private ResourceLoader resourceLoader;

	private HashMap<Long, Command> commandHash;

	private Config config;

	public Config getConfig() {
		if (this.config == null) {
			Resource resource = resourceLoader.getResource("classpath:/config/command-runner.yml");
			Yaml yaml = new Yaml();
			try {
				this.config = yaml.loadAs(new FileInputStream(resource.getFile()), Config.class);
				this.populateHashScripts();
			} catch (FileNotFoundException e) {
				logger.error("File not found in [classpath:/config/command-runner.yml]");
				// SpringApplication.exit(appContext, () -> 0);
			} catch (IOException e) {
				logger.error("File not found in [classpath:/config/command-runner.yml]");
				// SpringApplication.exit(appContext, () -> 0);
			} catch (YAMLException e) {
				logger.error("File with bad format [classpath:/config/command-runner.yml]");
				// SpringApplication.exit(appContext, () -> 0);
			}
		}
		return this.config;
	}

	private void populateHashScripts() {
		this.commandHash = new HashMap<>();
		for (Group group : this.config.getGroups()) {
			for (CommandGroup commandGroup : group.getCommandGroup()) {
				for (Command command : commandGroup.getCommands()) {
					this.commandHash.put(command.getId(), command);
				}
			}
		}
	}

	public Command getConfig(Long idCommand) {
		return this.commandHash.get(idCommand);
	}

}
