package com.commandrunner.component;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Profile({ "development", "test" })
@Component
public class ProjectDirectoryDevTstImpl implements ProjectDirectory {

	private static final Logger logger = LoggerFactory.getLogger(ProjectDirectoryDevTstImpl.class);

	private ShutdownApplication shutdownApplication;

	private String directory;

	private ResourceLoader resourceLoader;

	public ProjectDirectoryDevTstImpl(ResourceLoader resourceLoader, ShutdownApplication shutdownApplication) {
		this.resourceLoader = resourceLoader;
		this.shutdownApplication = shutdownApplication;
		this.loadScriptsDirectory();
	}

	private void loadScriptsDirectory() {
		try {
			Resource resource = this.resourceLoader.getResource("classpath:/scripts");

			this.directory = resource.getFile().getAbsolutePath();
			logger.info("Scripts files setted to [ " + this.directory + " ]");
		} catch (Exception e) {
			e.printStackTrace();
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
		return directory;
	}

	@Override
	public InputStream getFile(String path) {
		Resource resource = this.resourceLoader.getResource("classpath:" + path);
		InputStream inputStream = null;
		try {
			return resource.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error acessing path [ " + path + " ]");
			shutdownApplication.shutdown();
		}
		return inputStream;
	}

}
