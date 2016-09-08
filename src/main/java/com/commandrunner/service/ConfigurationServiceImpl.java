package com.commandrunner.service;

import java.io.InputStream;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.commandrunner.component.ProjectDirectory;
import com.commandrunner.component.ShutdownApplication;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

	private ShutdownApplication shutdownApplication;

	private CommandRunner commandRunner;

	private HashMap<Long, Command> commandHash;

	private Configuration configuration;

	private ProjectDirectory projectDirectory;

	/**
	 * Verify files and set permission to execute
	 */
	public ConfigurationServiceImpl(CommandRunner commandRunner, ProjectDirectory scriptsDirectory,
			ShutdownApplication shutdownApplication) {
		this.commandRunner = commandRunner;
		this.projectDirectory = scriptsDirectory;
		this.shutdownApplication = shutdownApplication;

		this.loadConfiguration();

		this.populateCommandHash();

		this.setPermissionToFiles();
	}

	private void setPermissionToFiles() {
		logger.info("Giving permission to execute files in : " + this.projectDirectory.getScriptsDirectory());
		Result result = this.commandRunner.run("sudo chmod +x *.sh", this.projectDirectory.getScriptsDirectory());
		if (result.getResultEnum().equals(ResultEnum.OK)) {
			logger.info("Permission has been given.");
		} else {
			logger.error("Permission denied.");
			shutdownApplication.shutdown();
		}

	}

	private void loadConfiguration() {
		// Load Configuration
		Yaml yaml = new Yaml();
		try {
			InputStream fileInputStream = this.projectDirectory.getFile("/config/command-runner.yml");
			this.configuration = yaml.loadAs(fileInputStream, Configuration.class);
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

	/* (non-Javadoc)
	 * @see com.commandrunner.service.IConfigurationService#getConfiguration()
	 */
	@Override
	public Configuration getConfiguration() {
		return this.configuration;
	}

	/* (non-Javadoc)
	 * @see com.commandrunner.service.IConfigurationService#getConfiguration(java.lang.Long)
	 */
	@Override
	public Command getConfiguration(Long idCommand) {
		return this.commandHash.get(idCommand);
	}

}
