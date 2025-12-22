package org.springframework.boot.autoconfigure.jdbc;

import ch.qos.logback.classic.ClassicConstants;
import io.vertx.ext.web.handler.FormLoginHandler;
import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.TransactionManager;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.jdbc.XADataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DataSourceProperties.class})
@Configuration
@ConditionalOnClass({DataSource.class, TransactionManager.class, EmbeddedDatabaseType.class})
@ConditionalOnMissingBean({DataSource.class})
@ConditionalOnBean({XADataSourceWrapper.class})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jdbc/XADataSourceAutoConfiguration.class */
public class XADataSourceAutoConfiguration implements BeanClassLoaderAware {
    private final XADataSourceWrapper wrapper;
    private final DataSourceProperties properties;
    private final XADataSource xaDataSource;
    private ClassLoader classLoader;

    public XADataSourceAutoConfiguration(XADataSourceWrapper wrapper, DataSourceProperties properties, ObjectProvider<XADataSource> xaDataSource) {
        this.wrapper = wrapper;
        this.properties = properties;
        this.xaDataSource = xaDataSource.getIfAvailable();
    }

    @Bean
    public DataSource dataSource() throws Exception {
        XADataSource xaDataSource = this.xaDataSource;
        if (xaDataSource == null) {
            xaDataSource = createXaDataSource();
        }
        return this.wrapper.mo5588wrapDataSource(xaDataSource);
    }

    @Override // org.springframework.beans.factory.BeanClassLoaderAware
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    private XADataSource createXaDataSource() throws LinkageError {
        String className = this.properties.getXa().getDataSourceClassName();
        if (!StringUtils.hasLength(className)) {
            className = DatabaseDriver.fromJdbcUrl(this.properties.determineUrl()).getXaDataSourceClassName();
        }
        Assert.state(StringUtils.hasLength(className), "No XA DataSource class name specified");
        XADataSource dataSource = createXaDataSourceInstance(className);
        bindXaProperties(dataSource, this.properties);
        return dataSource;
    }

    private XADataSource createXaDataSourceInstance(String className) throws LinkageError {
        try {
            Class<?> dataSourceClass = ClassUtils.forName(className, this.classLoader);
            Object instance = BeanUtils.instantiateClass(dataSourceClass);
            Assert.isInstanceOf(XADataSource.class, instance);
            return (XADataSource) instance;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create XADataSource instance from '" + className + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private void bindXaProperties(XADataSource target, DataSourceProperties dataSourceProperties) {
        Binder binder = new Binder(getBinderSource(dataSourceProperties));
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(target));
    }

    private ConfigurationPropertySource getBinderSource(DataSourceProperties dataSourceProperties) {
        MapConfigurationPropertySource source = new MapConfigurationPropertySource();
        source.put(ClassicConstants.USER_MDC_KEY, this.properties.determineUsername());
        source.put(FormLoginHandler.DEFAULT_PASSWORD_PARAM, this.properties.determinePassword());
        source.put("url", this.properties.determineUrl());
        source.putAll(dataSourceProperties.getXa().getProperties());
        ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();
        aliases.addAliases(ClassicConstants.USER_MDC_KEY, FormLoginHandler.DEFAULT_USERNAME_PARAM);
        return source.withAliases(aliases);
    }
}
