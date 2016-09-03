package com.commandrunner.component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.commandrunner.bean.Result;
import com.commandrunner.bean.ResultEnum;

/**
 * http://stackoverflow.com/questions/1410741/want-to-invoke-a-linux-shell- command-from-java
 */
@Component
public class CommandRunner {

	private static final String CR_MESSAGE = "CR-MESSAGE:";
	private static final Logger logger = LoggerFactory.getLogger(CommandRunner.class);

	/**
	 * Roda um comando no shell
	 * 
	 * @param commands
	 *            lista com os comandos
	 * @param dirCommand
	 *            diretorio onde o comando será executado
	 * @return texto com o retorno do shell
	 * @throws Exception
	 */
	public Result run(String command, String dirCommand) {
		StringBuilder out = new StringBuilder();
		Result result = new Result();
		try {
			logger.info("Executing... [" + command + "]");
			// Build command
			List<String> commands = new ArrayList<String>();
			commands.add("bash");
			commands.add("-c");
			commands.add(command);

			// Run macro on target
			ProcessBuilder pb = new ProcessBuilder(commands);
			if (dirCommand != null) {
				pb.directory(new File(dirCommand));
			}
			pb.redirectErrorStream(true);
			Process process = pb.start();

			// Read output
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null, previous = null;
			while ((line = br.readLine()) != null)
				if (!line.equals(previous)) {
					previous = line;
					out.append(line).append('\n');
					logger.info("returning... [" + line + "]");
					if (line.startsWith(CR_MESSAGE)) {
						result.setMessage(line.substring(CR_MESSAGE.length()));
					}
				}
			logger.info("returned... [" + out.toString() + "]");
			result.setResultEnum(ResultEnum.OK);
		} catch (Exception e) {
			logger.error("error executing [" + command + "]");
			e.printStackTrace();
			result.setResultEnum(ResultEnum.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
