package commandrunner.controller;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commandrunner.CommandRunnerApplication;
import com.commandrunner.bean.Config;
import com.commandrunner.controller.CommandController;
import com.commandrunner.controller.ConfigController;
import com.commandrunner.rest.bean.Result;
import com.commandrunner.rest.bean.ResultEnum;

@SpringBootTest(classes = CommandRunnerApplication.class)
@RunWith(SpringRunner.class)
public class CommandControllerTest {

	@Autowired
	private CommandController commandController;

	@Autowired
	private ConfigController configController;

	@Before
	public void before() throws Exception {
		// execute only on linux
		Assume.assumeTrue(System.getProperty("os.name").startsWith("Linux"));
	}

	@Test
	public void testCommand1() {
		Config config = this.configController.getConfig();
		String script = config.getGroups().get(0).getCommandGroup().get(0).getCommands().get(0).getScript();
		Assert.assertEquals("script1.sh", script);
		Long idCommand = config.getGroups().get(0).getCommandGroup().get(0).getCommands().get(0).getId();
		Result result = this.commandController.run(idCommand);
		Assert.assertEquals(ResultEnum.OK, result.getResultEnum());
	}

}
