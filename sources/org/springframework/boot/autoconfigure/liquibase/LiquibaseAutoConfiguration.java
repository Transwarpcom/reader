package org.springframework.boot.autoconfigure.liquibase;

import java.util.function.Supplier;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import liquibase.change.DatabaseChange;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcOperationsDependsOnPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.NamedParameterJdbcOperationsDependsOnPostProcessor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.util.Assert;

@Configuration
@ConditionalOnClass({SpringLiquibase.class, DatabaseChange.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ConditionalOnBean({DataSource.class})
@ConditionalOnProperty(prefix = "spring.liquibase", name = {"enabled"}, matchIfMissing = true)
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/liquibase/LiquibaseAutoConfiguration.class */
public class LiquibaseAutoConfiguration {
    @Bean
    public LiquibaseSchemaManagementProvider liquibaseDefaultDdlModeProvider(ObjectProvider<SpringLiquibase> liquibases) {
        return new LiquibaseSchemaManagementProvider(liquibases);
    }

    @EnableConfigurationProperties({DataSourceProperties.class, LiquibaseProperties.class})
    @Configuration
    @ConditionalOnMissingBean({SpringLiquibase.class})
    @Import({LiquibaseJpaDependencyConfiguration.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/liquibase/LiquibaseAutoConfiguration$LiquibaseConfiguration.class */
    public static class LiquibaseConfiguration {
        private final LiquibaseProperties properties;
        private final DataSourceProperties dataSourceProperties;
        private final ResourceLoader resourceLoader;
        private final DataSource dataSource;
        private final DataSource liquibaseDataSource;

        public LiquibaseConfiguration(LiquibaseProperties properties, DataSourceProperties dataSourceProperties, ResourceLoader resourceLoader, ObjectProvider<DataSource> dataSource, @LiquibaseDataSource ObjectProvider<DataSource> liquibaseDataSource) {
            this.properties = properties;
            this.dataSourceProperties = dataSourceProperties;
            this.resourceLoader = resourceLoader;
            this.dataSource = dataSource.getIfUnique();
            this.liquibaseDataSource = liquibaseDataSource.getIfAvailable();
        }

        @PostConstruct
        public void checkChangelogExists() {
            if (this.properties.isCheckChangeLogLocation()) {
                Resource resource = this.resourceLoader.getResource(this.properties.getChangeLog());
                Assert.state(resource.exists(), (Supplier<String>) () -> {
                    return "Cannot find changelog location: " + resource + " (please add changelog or check your Liquibase configuration)";
                });
            }
        }

        @Bean
        public SpringLiquibase liquibase() {
            SpringLiquibase liquibase = createSpringLiquibase();
            liquibase.setChangeLog(this.properties.getChangeLog());
            liquibase.setContexts(this.properties.getContexts());
            liquibase.setDefaultSchema(this.properties.getDefaultSchema());
            liquibase.setLiquibaseSchema(this.properties.getLiquibaseSchema());
            liquibase.setLiquibaseTablespace(this.properties.getLiquibaseTablespace());
            liquibase.setDatabaseChangeLogTable(this.properties.getDatabaseChangeLogTable());
            liquibase.setDatabaseChangeLogLockTable(this.properties.getDatabaseChangeLogLockTable());
            liquibase.setDropFirst(this.properties.isDropFirst());
            liquibase.setShouldRun(this.properties.isEnabled());
            liquibase.setLabels(this.properties.getLabels());
            liquibase.setChangeLogParameters(this.properties.getParameters());
            liquibase.setRollbackFile(this.properties.getRollbackFile());
            liquibase.setTestRollbackOnUpdate(this.properties.isTestRollbackOnUpdate());
            return liquibase;
        }

        private SpringLiquibase createSpringLiquibase() {
            DataSource liquibaseDataSource = getDataSource();
            if (liquibaseDataSource != null) {
                SpringLiquibase liquibase = new SpringLiquibase();
                liquibase.setDataSource(liquibaseDataSource);
                return liquibase;
            }
            SpringLiquibase liquibase2 = new DataSourceClosingSpringLiquibase();
            liquibase2.setDataSource(createNewDataSource());
            return liquibase2;
        }

        private DataSource getDataSource() {
            if (this.liquibaseDataSource != null) {
                return this.liquibaseDataSource;
            }
            if (this.properties.getUrl() == null && this.properties.getUser() == null) {
                return this.dataSource;
            }
            return null;
        }

        private DataSource createNewDataSource() {
            LiquibaseProperties liquibaseProperties = this.properties;
            liquibaseProperties.getClass();
            Supplier<String> supplier = liquibaseProperties::getUrl;
            DataSourceProperties dataSourceProperties = this.dataSourceProperties;
            dataSourceProperties.getClass();
            String url = getProperty(supplier, dataSourceProperties::determineUrl);
            LiquibaseProperties liquibaseProperties2 = this.properties;
            liquibaseProperties2.getClass();
            Supplier<String> supplier2 = liquibaseProperties2::getUser;
            DataSourceProperties dataSourceProperties2 = this.dataSourceProperties;
            dataSourceProperties2.getClass();
            String user = getProperty(supplier2, dataSourceProperties2::determineUsername);
            LiquibaseProperties liquibaseProperties3 = this.properties;
            liquibaseProperties3.getClass();
            Supplier<String> supplier3 = liquibaseProperties3::getPassword;
            DataSourceProperties dataSourceProperties3 = this.dataSourceProperties;
            dataSourceProperties3.getClass();
            String password = getProperty(supplier3, dataSourceProperties3::determinePassword);
            return DataSourceBuilder.create().url(url).username(user).password(password).build();
        }

        private String getProperty(Supplier<String> property, Supplier<String> defaultValue) {
            String value = property.get();
            return value != null ? value : defaultValue.get();
        }
    }

    @Configuration
    @ConditionalOnClass({LocalContainerEntityManagerFactoryBean.class})
    @ConditionalOnBean({AbstractEntityManagerFactoryBean.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/liquibase/LiquibaseAutoConfiguration$LiquibaseJpaDependencyConfiguration.class */
    protected static class LiquibaseJpaDependencyConfiguration extends EntityManagerFactoryDependsOnPostProcessor {
        public LiquibaseJpaDependencyConfiguration() {
            super("liquibase");
        }
    }

    @Configuration
    @ConditionalOnClass({JdbcOperations.class})
    @ConditionalOnBean({JdbcOperations.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/liquibase/LiquibaseAutoConfiguration$LiquibaseJdbcOperationsDependencyConfiguration.class */
    protected static class LiquibaseJdbcOperationsDependencyConfiguration extends JdbcOperationsDependsOnPostProcessor {
        public LiquibaseJdbcOperationsDependencyConfiguration() {
            super("liquibase");
        }
    }

    @Configuration
    @ConditionalOnClass({NamedParameterJdbcOperations.class})
    @ConditionalOnBean({NamedParameterJdbcOperations.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/liquibase/LiquibaseAutoConfiguration$LiquibaseNamedParameterJdbcOperationsDependencyConfiguration.class */
    protected static class LiquibaseNamedParameterJdbcOperationsDependencyConfiguration extends NamedParameterJdbcOperationsDependsOnPostProcessor {
        public LiquibaseNamedParameterJdbcOperationsDependencyConfiguration() {
            super("liquibase");
        }
    }
}
