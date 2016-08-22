package com.commandrunner.component;

import java.io.InputStream;

public interface ProjectDirectory {

	String getScriptsDirectory();

	InputStream getFile(String path);

}