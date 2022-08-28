package io.github.onotoliy.core;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Главный класс приложения.
 *
 * @author Anatoliy Pokhresnyi
 */
@Configuration
@ComponentScan
public class CoreConfiguration {

    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(CoreConfiguration.class);

    /**
     * Переменный окружения.
     */
    private final Environment environment;

    /**
     * Конструктор.
     *
     * @param environment Переменный окружения.
     */
    @Autowired
    public CoreConfiguration(final Environment environment) {
        this.environment = environment;
    }

    /**
     * Выполнение скриптов Liquibase.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(
            environment.getRequiredProperty(
                "spring.datasource.driver-class-name"
            )
        );
        ds.setUrl(
            environment.getRequiredProperty("spring.datasource.url")
        );
        ds.setUsername(
            environment.getRequiredProperty("spring.datasource.username")
        );
        ds.setPassword(
            environment.getRequiredProperty("spring.datasource.password")
        );

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(
            "classpath:/db/changelog/db.core.changelog-master.xml"
        );
        liquibase.setDataSource(ds);
        liquibase.setShouldRun(true);

        try {
            liquibase.afterPropertiesSet();
        } catch (LiquibaseException exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
    }
}
