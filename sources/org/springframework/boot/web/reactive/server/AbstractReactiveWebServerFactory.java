package org.springframework.boot.web.reactive.server;

import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/reactive/server/AbstractReactiveWebServerFactory.class */
public abstract class AbstractReactiveWebServerFactory extends AbstractConfigurableWebServerFactory implements ConfigurableReactiveWebServerFactory {
    public AbstractReactiveWebServerFactory() {
    }

    public AbstractReactiveWebServerFactory(int port) {
        super(port);
    }
}
