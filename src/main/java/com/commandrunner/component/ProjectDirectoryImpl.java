package com.commandrunner.component;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.commandrunner.condition.NoProfilesEnabled;

@NoProfilesEnabled({ "development", "test" })
@Component
public class ScriptsDirectoryImpl implements ScriptsDirectory {

	private static final Logger logger = LoggerFactory.getLogger(ScriptsDirectoryImpl.class);

	@Autowired
	private ShutdownApplication shutdownApplication;

	private String directory;

	public ScriptsDirectoryImpl(ResourceLoader resourceLoader) {
		this.loadScriptsDirectory(resourceLoader);
	}

	private void loadScriptsDirectory(ResourceLoader resourceLoader) {
		try {
			this.directory = new File("").getAbsolutePath() + "/scripts/";
			logger.info("Scripts files setted to [ " + this.directory + " ]");
		} catch (Exception e) {
			logger.error("Directory [ ./scripts/" + " ] does not exist.");
			shutdownApplication.shutdown();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.commandrunner.component.ScriptsDirectory#getDirectory()
	 */
	@Override
	public String getDirectory() {
		return directory;
	}

}
