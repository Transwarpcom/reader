package org.springframework.boot.autoconfigure.cache;

import java.util.Properties;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/cache/JCachePropertiesCustomizer.class */
interface JCachePropertiesCustomizer {
    void customize(CacheProperties cacheProperties, Properties properties);
}
