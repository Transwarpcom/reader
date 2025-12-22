package org.springframework.boot.autoconfigure.web.reactive.function.client;

import java.util.function.Function;
import org.eclipse.jetty.reactive.client.ReactiveRequest;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.http.client.reactive.JettyResourceFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/reactive/function/client/ClientHttpConnectorConfiguration.class */
class ClientHttpConnectorConfiguration {
    ClientHttpConnectorConfiguration() {
    }

    @ConditionalOnMissingBean({ClientHttpConnector.class})
    @Configuration
    @ConditionalOnClass({HttpClient.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/reactive/function/client/ClientHttpConnectorConfiguration$ReactorNetty.class */
    public static class ReactorNetty {
        @ConditionalOnMissingBean
        @Bean
        public ReactorResourceFactory reactorClientResourceFactory() {
            return new ReactorResourceFactory();
        }

        @Bean
        public ReactorClientHttpConnector reactorClientHttpConnector(ReactorResourceFactory reactorResourceFactory) {
            return new ReactorClientHttpConnector(reactorResourceFactory, Function.identity());
        }
    }

    @ConditionalOnMissingBean({ClientHttpConnector.class})
    @Configuration
    @ConditionalOnClass({ReactiveRequest.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/reactive/function/client/ClientHttpConnectorConfiguration$JettyClient.class */
    public static class JettyClient {
        @ConditionalOnMissingBean
        @Bean
        public JettyResourceFactory jettyClientResourceFactory() {
            return new JettyResourceFactory();
        }

        @Bean
        public JettyClientHttpConnector jettyClientHttpConnector(JettyResourceFactory jettyResourceFactory) {
            org.eclipse.jetty.client.HttpClient httpClient = new org.eclipse.jetty.client.HttpClient(new SslContextFactory.Client());
            httpClient.setExecutor(jettyResourceFactory.getExecutor());
            httpClient.setByteBufferPool(jettyResourceFactory.getByteBufferPool());
            httpClient.setScheduler(jettyResourceFactory.getScheduler());
            return new JettyClientHttpConnector(httpClient);
        }
    }
}
