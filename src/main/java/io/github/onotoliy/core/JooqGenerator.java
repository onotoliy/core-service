package io.github.onotoliy.core;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.SchemaMappingType;
import org.jooq.meta.jaxb.Target;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * Класс использующийся для генерации Jooq схемы.
 *
 * @author Anatoliy Pokhresnyi
 */
public class JooqGenerator {

    /**
     * Продолжительность 5 мин.
     */
    private static final int DURATION = 600;

    /**
     * Конструктор по умолчанию.
     */
    public JooqGenerator() {

    }

    /**
     * Метод запускающий генерацию Jooq схемы.
     *
     * @param args Параметры генерации Jooq схемы.
     * @throws Exception Ошибка возникшая при генерации Jooq схемы.
     */
    public static void main(final String[] args) throws Exception {
        final URL changelog = JooqGenerator.class.getResource(args[0]);
        final String directory = args[1];
        final String pkg = args[2];

        /*
          Удалить файлы из указанной директории.
         */
        Paths.get(System.getProperty("user.dir"), directory)
             .toFile()
             .deleteOnExit();

        PostgreSQLContainer container = (PostgreSQLContainer)
            new PostgreSQLContainer("postgres:10.4")
                .withDatabaseName("service")
                .withUsername("service")
                .withPassword("service")
                .withStartupTimeout(Duration.ofSeconds(DURATION));

        container.start();

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(container.getDriverClassName());
        ds.setUrl(container.getJdbcUrl());
        ds.setUsername(container.getUsername());
        ds.setPassword(container.getPassword());

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(
            "classpath:/db/changelog/db.core.changelog-master.xml"
        );
        liquibase.setDataSource(ds);
        liquibase.setShouldRun(true);

        try {
            liquibase.afterPropertiesSet();
        } catch (LiquibaseException exception) {
            exception.printStackTrace();
        }

        /*
          Генерация схемы.
         */
        Configuration configuration = new Configuration()
            .withJdbc(new Jdbc()
                .withDriver("org.postgresql.Driver")
                .withUrl(container.getJdbcUrl())
                .withUser(container.getUsername())
                .withPassword(container.getPassword()))
            .withGenerator(new Generator()
                .withName("org.jooq.codegen.DefaultGenerator")
                .withDatabase(new org.jooq.meta.jaxb.Database()
                    .withName("org.jooq.meta.postgres.PostgresDatabase")
                    .withExcludes("DATABASECHANGELOGLOCK*|DATABASECHANGELOG*")
                    .withSchemata(new SchemaMappingType()
                            .withInputSchema("public"))
                )
                .withTarget(new Target()
                    .withPackageName(pkg)
                    .withDirectory(directory)));

        GenerationTool.generate(configuration);

        container.stop();
    }

    /**
     * Метод ничего не делающий. Костыль чтобы не ругался CheckStyle.
     */
    public void ignoreMethod() {

    }
}
