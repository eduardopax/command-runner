package com.commandrunner.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import com.commandrunner.bean.Command;
import com.commandrunner.bean.CommandGroup;
import com.commandrunner.bean.Configuration;
import com.commandrunner.bean.Group;
import com.commandrunner.bean.Result;
import com.commandrunner.bean.ResultEnum;
import com.commandrunner.component.CommandRunner;
import com.commandrunner.component.ScriptsDirectory;
import com.commandrunner.component.ShutdownApplication;

@Service
public class ConfigurationService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

	@Autowired
	private ShutdownApplication shutdownApplication;

	private CommandRunner commandRunner;

	private HashMap<Long, Command> commandHash;

	private Configuration configuration;

	private ScriptsDirectory scriptsDirectory;

	/**
	 * Verify files and set permission to execute
	 */
	public ConfigurationService(ResourceLoader resourceLoader, CommandRunner commandRunner,
			ScriptsDirectory scriptsDirectory) {
		this.commandRunner = commandRunner;
		this.scriptsDirectory = scriptsDirectory;

		this.loadConfiguration(resourceLoader);

		this.populateCommandHash();

		this.setPermissionToFiles();
	}

	private void setPermissionToFiles() {
		logger.info("Giving permission to execute files in : " + this.scriptsDirectory.getDirectory());
		Result result = this.commandRunner.run("sudo chmod +x *.sh", this.scriptsDirectory.getDirectory());
		if (result.getResultEnum().equals(ResultEnum.OK)) {
			logger.info("Permission has been given.");
		} else {
			logger.error("Permission denied.");
			shutdownApplication.shutdown();
		}

	}

	private void loadConfiguration(ResourceLoader resourceLoader) {
		// Load Configuration
		Resource resource = resourceLoader.getResource("classpath:/config/command-runner.yml");
		Yaml yaml = new Yaml();
		try {
			this.configuration = yaml.loadAs(resource.getInputStream(), Configuration.class);
		} catch (FileNotFoundException e) {
			logger.error("File not found in [classpath:/config/command-runner.yml]");
			shutdownApplication.shutdown();
		} catch (IOException e) {
			logger.error("File not found in [classpath:/config/command-runner.yml]");
			shutdownApplication.shutdown();
		} catch (YAMLException e) {
			logger.error("File with bad format [classpath:/config/command-runner.yml]");
			shutdownApplication.shutdown();
		}
	}

	private void populateCommandHash() {
		this.commandHash = new HashMap<>();
		for (Group group : this.configuration.getGroups()) {
			for (CommandGroup commandGroup : group.getCommandGroup()) {
				for (Command command : commandGroup.getCommands()) {
					this.commandHash.put(command.getId(), command);
				}
			}
		}
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}

	public Command getConfiguration(Long idCommand) {
		return this.commandHash.get(idCommand);
	}

}
