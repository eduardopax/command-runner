package commandrunner.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import com.commandrunner.CommandRunnerApplication;
import com.commandrunner.bean.Configuration;

@SpringBootTest(classes = CommandRunnerApplication.class)
@RunWith(SpringRunner.class)
public class ConfigTest {

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void getConfigOK() {
		Resource resource = resourceLoader.getResource("classpath:/config/command-runner.yml");
		Yaml yaml = new Yaml();
		Configuration config = null;
		try {
			config = yaml.loadAs(new FileInputStream(resource.getFile()), Configuration.class);
		} catch (IOException e) {
			Assert.fail();
		}
		Assert.assertNotNull(config);
		Assert.assertSame(config.getUsers().size(), 1);
	}

	@Test
	public void getConfigError() {
		Resource resource = resourceLoader.getResource("classpath:/config/command-runner-error.yml");
		Yaml yaml = new Yaml();
		try {
			Configuration config = yaml.loadAs(new FileInputStream(resource.getFile()), Configuration.class);
			Assert.fail("It can not load");
		} catch (FileNotFoundException e) {
			Assert.fail("It can not load");
		} catch (IOException e) {
			Assert.fail("It can not load");
		} catch (YAMLException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void getCommands() {
		Configuration config = loadFile();
		Assert.assertNotNull(config);
		Assert.assertEquals("script1.sh",
				config.getGroups().get(0).getCommandGroup().get(0).getCommands().get(0).getScript());
		Assert.assertEquals("On", config.getGroups().get(0).getCommandGroup().get(0).getCommands().get(0).getName());
	}

	@Test
	public void getApplicarion2() {
		Configuration config = loadFile();
		Assert.assertNotNull(config);
		Assert.assertEquals("Application 2", config.getGroups().get(0).getCommandGroup().get(1).getName());
	}

	private Configuration loadFile() {
		Resource resource = resourceLoader.getResource("classpath:/config/command-runner.yml");
		Yaml yaml = new Yaml();
		Configuration config = null;
		try {
			config = yaml.loadAs(new FileInputStream(resource.getFile()), Configuration.class);
		} catch (FileNotFoundException e) {
			Assert.fail("It can not load");
		} catch (IOException e) {
			Assert.fail("It can not load");
		} catch (YAMLException e) {
			e.printStackTrace();
			Assert.fail("It can not load");
		}
		return config;
	}

}
