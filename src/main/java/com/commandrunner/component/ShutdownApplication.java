package com.commandrunner.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ShutdownApplication {

	private static final Logger logger = LoggerFactory.getLogger(ShutdownApplication.class);

	private ApplicationContext appContext;

	public ShutdownApplication(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public void shutdown() {
		logger.error("Shutdown application");
		SpringApplication.exit(appContext, () -> 0);
	}

}
