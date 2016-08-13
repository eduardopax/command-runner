package com.commandrunner.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class ShutdownApplication {

	private static final Logger logger = LoggerFactory.getLogger(ShutdownApplication.class);

	@Autowired
	private ApplicationContext appContext;

	public void shutdown() {
		logger.error("Shutdown application");
		SpringApplication.exit(appContext, () -> 0);
	}

}
