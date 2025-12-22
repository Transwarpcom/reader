package org.springframework.boot.autoconfigure.web.reactive;

import org.springframework.web.reactive.config.ResourceHandlerRegistration;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/reactive/ResourceHandlerRegistrationCustomizer.class */
public interface ResourceHandlerRegistrationCustomizer {
    void customize(ResourceHandlerRegistration registration);
}
