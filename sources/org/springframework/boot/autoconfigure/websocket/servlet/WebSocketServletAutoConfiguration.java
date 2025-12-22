package org.springframework.boot.autoconfigure.websocket.servlet;

import io.undertow.websockets.jsr.Bootstrap;
import javax.servlet.Servlet;
import javax.websocket.server.ServerContainer;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.websocket.server.WsSci;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfigureBefore({ServletWebServerFactoryAutoConfiguration.class})
@Configuration
@ConditionalOnClass({Servlet.class, ServerContainer.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/websocket/servlet/WebSocketServletAutoConfiguration.class */
public class WebSocketServletAutoConfiguration {

    @Configuration
    @ConditionalOnClass({Tomcat.class, WsSci.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/websocket/servlet/WebSocketServletAutoConfiguration$TomcatWebSocketConfiguration.class */
    static class TomcatWebSocketConfiguration {
        TomcatWebSocketConfiguration() {
        }

        @ConditionalOnMissingBean(name = {"websocketServletWebServerCustomizer"})
        @Bean
        public TomcatWebSocketServletWebServerCustomizer websocketServletWebServerCustomizer() {
            return new TomcatWebSocketServletWebServerCustomizer();
        }
    }

    @Configuration
    @ConditionalOnClass({WebSocketServerContainerInitializer.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/websocket/servlet/WebSocketServletAutoConfiguration$JettyWebSocketConfiguration.class */
    static class JettyWebSocketConfiguration {
        JettyWebSocketConfiguration() {
        }

        @ConditionalOnMissingBean(name = {"websocketServletWebServerCustomizer"})
        @Bean
        public JettyWebSocketServletWebServerCustomizer websocketServletWebServerCustomizer() {
            return new JettyWebSocketServletWebServerCustomizer();
        }
    }

    @Configuration
    @ConditionalOnClass({Bootstrap.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/websocket/servlet/WebSocketServletAutoConfiguration$UndertowWebSocketConfiguration.class */
    static class UndertowWebSocketConfiguration {
        UndertowWebSocketConfiguration() {
        }

        @ConditionalOnMissingBean(name = {"websocketServletWebServerCustomizer"})
        @Bean
        public UndertowWebSocketServletWebServerCustomizer websocketServletWebServerCustomizer() {
            return new UndertowWebSocketServletWebServerCustomizer();
        }
    }
}
