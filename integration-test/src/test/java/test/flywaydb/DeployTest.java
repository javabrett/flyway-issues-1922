package test.flywaydb;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DeployTest {

	@Test
	public void deploy() throws GlassFishException, InterruptedException {
		final GlassFishProperties glassfishProperties = new GlassFishProperties();
		glassfishProperties.setPort("http-listener", 8080);
		glassfishProperties.setPort("https-listener", 8181);

		final GlassFish glassfish = GlassFishRuntime.bootstrap().newGlassFish(glassfishProperties);
		try {
			glassfish.start();

			final Deployer deployer = glassfish.getDeployer();
			deployer.deploy(new File("/Users/bsrandal/git/flywaydb-ear-test/build/libs/flywaydb-ear-test.ear"));

			final Collection<String> deployedApplications = glassfish.getDeployer().getDeployedApplications();
			assertEquals(deployedApplications, Arrays.asList("flywaydb-ear-test"));

		} finally {
			glassfish.stop();
		}
	}
}
