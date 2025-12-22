package org.springframework.boot.web.embedded.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.ForwardedRequestCustomizer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.Server;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/jetty/ForwardHeadersCustomizer.class */
class ForwardHeadersCustomizer implements JettyServerCustomizer {
    ForwardHeadersCustomizer() {
    }

    @Override // org.springframework.boot.web.embedded.jetty.JettyServerCustomizer
    public void customize(Server server) {
        ForwardedRequestCustomizer customizer = new ForwardedRequestCustomizer();
        for (Connector connector : server.getConnectors()) {
            for (HttpConfiguration.ConnectionFactory connectionFactory : connector.getConnectionFactories()) {
                if (connectionFactory instanceof HttpConfiguration.ConnectionFactory) {
                    connectionFactory.getHttpConfiguration().addCustomizer(customizer);
                }
            }
        }
    }
}
