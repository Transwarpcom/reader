package org.springframework.boot.autoconfigure.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.SocketOptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@EnableConfigurationProperties({CassandraProperties.class})
@Configuration
@ConditionalOnClass({Cluster.class})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/cassandra/CassandraAutoConfiguration.class */
public class CassandraAutoConfiguration {
    private final CassandraProperties properties;
    private final ObjectProvider<ClusterBuilderCustomizer> builderCustomizers;

    public CassandraAutoConfiguration(CassandraProperties properties, ObjectProvider<ClusterBuilderCustomizer> builderCustomizers) {
        this.properties = properties;
        this.builderCustomizers = builderCustomizers;
    }

    @ConditionalOnMissingBean
    @Bean
    public Cluster cassandraCluster() {
        PropertyMapper map = PropertyMapper.get();
        CassandraProperties properties = this.properties;
        Cluster.Builder builder = Cluster.builder().withClusterName(properties.getClusterName()).withPort(properties.getPort());
        properties.getClass();
        map.from(properties::getUsername).whenNonNull().to(username -> {
            builder.withCredentials(username, properties.getPassword());
        });
        properties.getClass();
        PropertyMapper.Source sourceWhenNonNull = map.from(properties::getCompression).whenNonNull();
        builder.getClass();
        sourceWhenNonNull.to(builder::withCompression);
        properties.getClass();
        PropertyMapper.Source sourceAs = map.from(properties::getLoadBalancingPolicy).whenNonNull().as(BeanUtils::instantiateClass);
        builder.getClass();
        sourceAs.to(builder::withLoadBalancingPolicy);
        PropertyMapper.Source sourceFrom = map.from(this::getQueryOptions);
        builder.getClass();
        sourceFrom.to(builder::withQueryOptions);
        properties.getClass();
        PropertyMapper.Source sourceAs2 = map.from(properties::getReconnectionPolicy).whenNonNull().as(BeanUtils::instantiateClass);
        builder.getClass();
        sourceAs2.to(builder::withReconnectionPolicy);
        properties.getClass();
        PropertyMapper.Source sourceAs3 = map.from(properties::getRetryPolicy).whenNonNull().as(BeanUtils::instantiateClass);
        builder.getClass();
        sourceAs3.to(builder::withRetryPolicy);
        PropertyMapper.Source sourceFrom2 = map.from(this::getSocketOptions);
        builder.getClass();
        sourceFrom2.to(builder::withSocketOptions);
        properties.getClass();
        PropertyMapper.Source sourceWhenTrue = map.from(properties::isSsl).whenTrue();
        builder.getClass();
        sourceWhenTrue.toCall(builder::withSSL);
        PropertyMapper.Source sourceFrom3 = map.from(this::getPoolingOptions);
        builder.getClass();
        sourceFrom3.to(builder::withPoolingOptions);
        properties.getClass();
        PropertyMapper.Source sourceAs4 = map.from(properties::getContactPoints).as((v0) -> {
            return StringUtils.toStringArray(v0);
        });
        builder.getClass();
        sourceAs4.to(builder::addContactPoints);
        properties.getClass();
        PropertyMapper.Source sourceWhenFalse = map.from(properties::isJmxEnabled).whenFalse();
        builder.getClass();
        sourceWhenFalse.toCall(builder::withoutJMXReporting);
        customize(builder);
        return builder.build();
    }

    private void customize(Cluster.Builder builder) {
        this.builderCustomizers.orderedStream().forEach(customizer -> {
            customizer.customize(builder);
        });
    }

    private QueryOptions getQueryOptions() {
        PropertyMapper map = PropertyMapper.get();
        QueryOptions options = new QueryOptions();
        CassandraProperties properties = this.properties;
        properties.getClass();
        PropertyMapper.Source sourceWhenNonNull = map.from(properties::getConsistencyLevel).whenNonNull();
        options.getClass();
        sourceWhenNonNull.to(options::setConsistencyLevel);
        properties.getClass();
        PropertyMapper.Source sourceWhenNonNull2 = map.from(properties::getSerialConsistencyLevel).whenNonNull();
        options.getClass();
        sourceWhenNonNull2.to(options::setSerialConsistencyLevel);
        properties.getClass();
        PropertyMapper.Source sourceFrom = map.from(properties::getFetchSize);
        options.getClass();
        sourceFrom.to((v1) -> {
            r1.setFetchSize(v1);
        });
        return options;
    }

    private SocketOptions getSocketOptions() {
        PropertyMapper map = PropertyMapper.get();
        SocketOptions options = new SocketOptions();
        CassandraProperties cassandraProperties = this.properties;
        cassandraProperties.getClass();
        PropertyMapper.Source<Integer> sourceAsInt = map.from(cassandraProperties::getConnectTimeout).whenNonNull().asInt((v0) -> {
            return v0.toMillis();
        });
        options.getClass();
        sourceAsInt.to((v1) -> {
            r1.setConnectTimeoutMillis(v1);
        });
        CassandraProperties cassandraProperties2 = this.properties;
        cassandraProperties2.getClass();
        PropertyMapper.Source<Integer> sourceAsInt2 = map.from(cassandraProperties2::getReadTimeout).whenNonNull().asInt((v0) -> {
            return v0.toMillis();
        });
        options.getClass();
        sourceAsInt2.to((v1) -> {
            r1.setReadTimeoutMillis(v1);
        });
        return options;
    }

    private PoolingOptions getPoolingOptions() {
        PropertyMapper map = PropertyMapper.get();
        CassandraProperties.Pool properties = this.properties.getPool();
        PoolingOptions options = new PoolingOptions();
        properties.getClass();
        PropertyMapper.Source<Integer> sourceAsInt = map.from(properties::getIdleTimeout).whenNonNull().asInt((v0) -> {
            return v0.getSeconds();
        });
        options.getClass();
        sourceAsInt.to((v1) -> {
            r1.setIdleTimeoutSeconds(v1);
        });
        properties.getClass();
        PropertyMapper.Source<Integer> sourceAsInt2 = map.from(properties::getPoolTimeout).whenNonNull().asInt((v0) -> {
            return v0.toMillis();
        });
        options.getClass();
        sourceAsInt2.to((v1) -> {
            r1.setPoolTimeoutMillis(v1);
        });
        properties.getClass();
        PropertyMapper.Source<Integer> sourceAsInt3 = map.from(properties::getHeartbeatInterval).whenNonNull().asInt((v0) -> {
            return v0.getSeconds();
        });
        options.getClass();
        sourceAsInt3.to((v1) -> {
            r1.setHeartbeatIntervalSeconds(v1);
        });
        properties.getClass();
        PropertyMapper.Source sourceFrom = map.from(properties::getMaxQueueSize);
        options.getClass();
        sourceFrom.to((v1) -> {
            r1.setMaxQueueSize(v1);
        });
        return options;
    }
}
