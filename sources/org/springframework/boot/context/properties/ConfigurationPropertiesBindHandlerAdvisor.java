package org.springframework.boot.context.properties;

import org.springframework.boot.context.properties.bind.BindHandler;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/ConfigurationPropertiesBindHandlerAdvisor.class */
public interface ConfigurationPropertiesBindHandlerAdvisor {
    BindHandler apply(BindHandler bindHandler);
}
