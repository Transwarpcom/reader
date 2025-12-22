package org.springframework.boot.autoconfigure.jersey;

import org.glassfish.jersey.server.ResourceConfig;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jersey/ResourceConfigCustomizer.class */
public interface ResourceConfigCustomizer {
    void customize(ResourceConfig config);
}
