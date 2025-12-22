package org.springframework.boot.autoconfigure.web.embedded;

import io.netty.channel.ChannelOption;
import java.time.Duration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.util.unit.DataSize;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/embedded/NettyWebServerFactoryCustomizer.class */
public class NettyWebServerFactoryCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory>, Ordered {
    private final Environment environment;
    private final ServerProperties serverProperties;

    public NettyWebServerFactoryCustomizer(Environment environment, ServerProperties serverProperties) {
        this.environment = environment;
        this.serverProperties = serverProperties;
    }

    @Override // org.springframework.core.Ordered
    public int getOrder() {
        return 0;
    }

    @Override // org.springframework.boot.web.server.WebServerFactoryCustomizer
    public void customize(NettyReactiveWebServerFactory factory) {
        factory.setUseForwardHeaders(getOrDeduceUseForwardHeaders(this.serverProperties, this.environment));
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        ServerProperties serverProperties = this.serverProperties;
        serverProperties.getClass();
        propertyMapper.from(serverProperties::getMaxHttpHeaderSize).to(maxHttpRequestHeaderSize -> {
            customizeMaxHttpHeaderSize(factory, maxHttpRequestHeaderSize);
        });
        ServerProperties serverProperties2 = this.serverProperties;
        serverProperties2.getClass();
        propertyMapper.from(serverProperties2::getConnectionTimeout).to(connectionTimeout -> {
            customizeConnectionTimeout(factory, connectionTimeout);
        });
    }

    private boolean getOrDeduceUseForwardHeaders(ServerProperties serverProperties, Environment environment) {
        if (serverProperties.isUseForwardHeaders() != null) {
            return serverProperties.isUseForwardHeaders().booleanValue();
        }
        CloudPlatform platform = CloudPlatform.getActive(environment);
        return platform != null && platform.isUsingForwardHeaders();
    }

    private void customizeMaxHttpHeaderSize(NettyReactiveWebServerFactory factory, DataSize maxHttpHeaderSize) {
        factory.addServerCustomizers(httpServer -> {
            return httpServer.httpRequestDecoder(httpRequestDecoderSpec -> {
                return httpRequestDecoderSpec.maxHeaderSize((int) maxHttpHeaderSize.toBytes());
            });
        });
    }

    private void customizeConnectionTimeout(NettyReactiveWebServerFactory factory, Duration connectionTimeout) {
        if (!connectionTimeout.isZero()) {
            long timeoutMillis = connectionTimeout.isNegative() ? 0L : connectionTimeout.toMillis();
            factory.addServerCustomizers(httpServer -> {
                return httpServer.tcpConfiguration(tcpServer -> {
                    return tcpServer.selectorOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf((int) timeoutMillis));
                });
            });
        }
    }
}
