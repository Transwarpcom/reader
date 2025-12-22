package org.springframework.boot.web.embedded.jetty;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/jetty/ConfigurableJettyWebServerFactory.class */
public interface ConfigurableJettyWebServerFactory extends ConfigurableWebServerFactory {
    void setAcceptors(int acceptors);

    void setSelectors(int selectors);

    void setUseForwardHeaders(boolean useForwardHeaders);

    void addServerCustomizers(JettyServerCustomizer... customizers);
}
