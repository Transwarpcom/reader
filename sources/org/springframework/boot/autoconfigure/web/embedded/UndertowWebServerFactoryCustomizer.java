package org.springframework.boot.autoconfigure.web.embedded;

import io.undertow.UndertowOptions;
import java.time.Duration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/embedded/UndertowWebServerFactoryCustomizer.class */
public class UndertowWebServerFactoryCustomizer implements WebServerFactoryCustomizer<ConfigurableUndertowWebServerFactory>, Ordered {
    private final Environment environment;
    private final ServerProperties serverProperties;

    public UndertowWebServerFactoryCustomizer(Environment environment, ServerProperties serverProperties) {
        this.environment = environment;
        this.serverProperties = serverProperties;
    }

    @Override // org.springframework.core.Ordered
    public int getOrder() {
        return 0;
    }

    @Override // org.springframework.boot.web.server.WebServerFactoryCustomizer
    public void customize(ConfigurableUndertowWebServerFactory factory) {
        ServerProperties properties = this.serverProperties;
        ServerProperties.Undertow undertowProperties = properties.getUndertow();
        ServerProperties.Undertow.Accesslog accesslogProperties = undertowProperties.getAccesslog();
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        undertowProperties.getClass();
        PropertyMapper.Source<Integer> sourceAsInt = propertyMapper.from(undertowProperties::getBufferSize).whenNonNull().asInt((v0) -> {
            return v0.toBytes();
        });
        factory.getClass();
        sourceAsInt.to(factory::setBufferSize);
        undertowProperties.getClass();
        PropertyMapper.Source sourceFrom = propertyMapper.from(undertowProperties::getIoThreads);
        factory.getClass();
        sourceFrom.to(factory::setIoThreads);
        undertowProperties.getClass();
        PropertyMapper.Source sourceFrom2 = propertyMapper.from(undertowProperties::getWorkerThreads);
        factory.getClass();
        sourceFrom2.to(factory::setWorkerThreads);
        undertowProperties.getClass();
        PropertyMapper.Source sourceFrom3 = propertyMapper.from(undertowProperties::getDirectBuffers);
        factory.getClass();
        sourceFrom3.to(factory::setUseDirectBuffers);
        accesslogProperties.getClass();
        PropertyMapper.Source sourceFrom4 = propertyMapper.from(accesslogProperties::isEnabled);
        factory.getClass();
        sourceFrom4.to((v1) -> {
            r1.setAccessLogEnabled(v1);
        });
        accesslogProperties.getClass();
        PropertyMapper.Source sourceFrom5 = propertyMapper.from(accesslogProperties::getDir);
        factory.getClass();
        sourceFrom5.to(factory::setAccessLogDirectory);
        accesslogProperties.getClass();
        PropertyMapper.Source sourceFrom6 = propertyMapper.from(accesslogProperties::getPattern);
        factory.getClass();
        sourceFrom6.to(factory::setAccessLogPattern);
        accesslogProperties.getClass();
        PropertyMapper.Source sourceFrom7 = propertyMapper.from(accesslogProperties::getPrefix);
        factory.getClass();
        sourceFrom7.to(factory::setAccessLogPrefix);
        accesslogProperties.getClass();
        PropertyMapper.Source sourceFrom8 = propertyMapper.from(accesslogProperties::getSuffix);
        factory.getClass();
        sourceFrom8.to(factory::setAccessLogSuffix);
        accesslogProperties.getClass();
        PropertyMapper.Source sourceFrom9 = propertyMapper.from(accesslogProperties::isRotate);
        factory.getClass();
        sourceFrom9.to((v1) -> {
            r1.setAccessLogRotate(v1);
        });
        PropertyMapper.Source sourceFrom10 = propertyMapper.from(this::getOrDeduceUseForwardHeaders);
        factory.getClass();
        sourceFrom10.to((v1) -> {
            r1.setUseForwardHeaders(v1);
        });
        properties.getClass();
        propertyMapper.from(properties::getMaxHttpHeaderSize).whenNonNull().asInt((v0) -> {
            return v0.toBytes();
        }).when((v1) -> {
            return isPositive(v1);
        }).to(maxHttpHeaderSize -> {
            customizeMaxHttpHeaderSize(factory, maxHttpHeaderSize.intValue());
        });
        undertowProperties.getClass();
        propertyMapper.from(undertowProperties::getMaxHttpPostSize).asInt((v0) -> {
            return v0.toBytes();
        }).when((v1) -> {
            return isPositive(v1);
        }).to(maxHttpPostSize -> {
            customizeMaxHttpPostSize(factory, maxHttpPostSize.intValue());
        });
        properties.getClass();
        propertyMapper.from(properties::getConnectionTimeout).to(connectionTimeout -> {
            customizeConnectionTimeout(factory, connectionTimeout);
        });
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            deploymentInfo.setEagerFilterInit(undertowProperties.isEagerFilterInit());
        });
    }

    private boolean isPositive(Number value) {
        return value.longValue() > 0;
    }

    private void customizeConnectionTimeout(ConfigurableUndertowWebServerFactory factory, Duration connectionTimeout) {
        factory.addBuilderCustomizers(builder -> {
            builder.setServerOption(UndertowOptions.NO_REQUEST_TIMEOUT, Integer.valueOf((int) connectionTimeout.toMillis()));
        });
    }

    private void customizeMaxHttpHeaderSize(ConfigurableUndertowWebServerFactory factory, int maxHttpHeaderSize) {
        factory.addBuilderCustomizers(builder -> {
            builder.setServerOption(UndertowOptions.MAX_HEADER_SIZE, Integer.valueOf(maxHttpHeaderSize));
        });
    }

    private void customizeMaxHttpPostSize(ConfigurableUndertowWebServerFactory factory, long maxHttpPostSize) {
        factory.addBuilderCustomizers(builder -> {
            builder.setServerOption(UndertowOptions.MAX_ENTITY_SIZE, Long.valueOf(maxHttpPostSize));
        });
    }

    private boolean getOrDeduceUseForwardHeaders() {
        if (this.serverProperties.isUseForwardHeaders() != null) {
            return this.serverProperties.isUseForwardHeaders().booleanValue();
        }
        CloudPlatform platform = CloudPlatform.getActive(this.environment);
        return platform != null && platform.isUsingForwardHeaders();
    }
}
