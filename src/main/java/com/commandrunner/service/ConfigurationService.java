package com.commandrunner.service;

import com.commandrunner.bean.Command;
import com.commandrunner.bean.Configuration;

public interface ConfigurationService {

	Configuration getConfiguration();

	Command getConfiguration(Long idCommand);

}