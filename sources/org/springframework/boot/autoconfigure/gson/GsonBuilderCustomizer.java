package org.springframework.boot.autoconfigure.gson;

import com.google.gson.GsonBuilder;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/gson/GsonBuilderCustomizer.class */
public interface GsonBuilderCustomizer {
    void customize(GsonBuilder gsonBuilder);
}
