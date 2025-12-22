package org.springframework.boot.web.embedded.undertow;

import io.undertow.Undertow;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/undertow/UndertowBuilderCustomizer.class */
public interface UndertowBuilderCustomizer {
    void customize(Undertow.Builder builder);
}
