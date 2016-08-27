package com.commandrunner.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.commandrunner.condition.NoProfilesEnabled;

@NoProfilesEnabled({ "development", "test" })
@Component
public class ProjectDirectoryImpl implements ProjectDirectory {

	private static final Logger logger = LoggerFactory.getLogger(ProjectDirectoryImpl.class);

	private ShutdownApplication shutdownApplication;

	private String scriptsDirectory;

	private String directory;

	public ProjectDirectoryImpl(ResourceLoader resourceLoader, ShutdownApplication shutdownApplication) {
		this.shutdownApplication = shutdownApplication;
		this.load(resourceLoader);
	}

	private void load(ResourceLoader resourceLoader) {
		try {
			this.scriptsDirectory = new File("").getAbsolutePath() + "/scripts/";
			this.directory = new File("").getAbsolutePath();
			logger.info("Scripts files setted to [ " + this.scriptsDirectory + " ]");
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
	public String getScriptsDirectory() {
		return scriptsDirectory;
	}

	@Override
	public InputStream getFile(String path) {
		InputStream inputStream = null;
		try {
			File file = new File(this.directory + path);
			inputStream = new FileInputStream(file);
		} catch (Exception e) {
			logger.error("Path not exist [ " + path + " ]");
			shutdownApplication.shutdown();
		}
		return inputStream;
	}

}
