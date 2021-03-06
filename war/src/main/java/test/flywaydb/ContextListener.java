package test.flywaydb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			logger.info("TCCL: " + Thread.currentThread().getContextClassLoader());
			logger.info(getClass().getName() + " ClassLoader: " + this.getClass().getClassLoader());
			try {
				logger.info("org.flywaydb.core.api.logging.LogFactory ClassLoader: {}", Class.forName("org.flywaydb.core.api.logging.LogFactory").getClassLoader());
				Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("org.flywaydb.core.internal.util.logging.slf4j.Slf4jLogCreator");
				logger.info("Requested load of Slf4jLogCreator with TCCL, it loaded with :" + clazz.getClassLoader());
			} catch (ClassNotFoundException e) {
				logger.error("Can't load class org.flywaydb.core.api.logging.LogFactory", e);
			}

			Flyway flyway = new Flyway();
			flyway.baseline();
			logger.info("Flyway baselined.");
		} catch (Throwable t) {
			logger.error("Exception in contextInitialized", t);
			throw t;
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
