package com.commandrunner.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Profile({ "development", "test" })
@Component
public class ScriptsDirectoryDevTstImpl implements ScriptsDirectory {

	private static final Logger logger = LoggerFactory.getLogger(ScriptsDirectoryDevTstImpl.class);

	@Autowired
	private ShutdownApplication shutdownApplication;

	private String directory;

	public ScriptsDirectoryDevTstImpl(ResourceLoader resourceLoader) {
		this.loadScriptsDirectory(resourceLoader);
	}

	private void loadScriptsDirectory(ResourceLoader resourceLoader) {
		try {
			Resource resource = resourceLoader.getResource("classpath:/scripts");

			this.directory = resource.getFile().getAbsolutePath();
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
