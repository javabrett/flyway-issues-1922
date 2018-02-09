# Test-code for Flyway issue 1922

Test-code demonstrating issue https://github.com/flyway/flyway/issues/1922 .
    
Running `./gradlew clean assemble test` will cause a deployment failure in embedded Glassfish
caused by:

```
Caused by: java.lang.NoClassDefFoundError: org/slf4j/LoggerFactory
    at org.flywaydb.core.internal.util.logging.slf4j.Slf4jLogCreator.createLogger(Slf4jLogCreator.java:27)
    at org.flywaydb.core.api.logging.LogFactory.getLog(LogFactory.java:82)
    at org.flywaydb.core.internal.util.ClassUtils.<clinit>(ClassUtils.java:37)
    at org.flywaydb.core.internal.util.FeatureDetector.isSlf4jAvailable(FeatureDetector.java:96)
    at org.flywaydb.core.api.logging.LogFactory.getLog(LogFactory.java:71)
    at org.flywaydb.core.internal.util.FeatureDetector.<clinit>(FeatureDetector.java:25)
    at org.flywaydb.core.api.logging.LogFactory.getLog(LogFactory.java:68)
    at org.flywaydb.core.Flyway.<clinit>(Flyway.java:72)
    at test.flywaydb.ContextListener.contextInitialized(ContextListener.java:26)
```
