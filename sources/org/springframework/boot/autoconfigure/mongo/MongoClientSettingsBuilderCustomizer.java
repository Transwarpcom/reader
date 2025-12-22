package org.springframework.boot.autoconfigure.mongo;

import com.mongodb.MongoClientSettings;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/mongo/MongoClientSettingsBuilderCustomizer.class */
public interface MongoClientSettingsBuilderCustomizer {
    void customize(MongoClientSettings.Builder clientSettingsBuilder);
}
