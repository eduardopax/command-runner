package commandrunner.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commandrunner.CommandRunnerApplication;
import com.commandrunner.controller.ConfigController;

@SpringBootTest(classes = CommandRunnerApplication.class)
@RunWith(SpringRunner.class)
public class ConfigControllerTest {

	@Autowired
	private ConfigController configController;

	@Test
	public void loadConfig() {
		Assert.assertNotNull(this.configController.getConfig());
	}

}
