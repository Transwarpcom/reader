package org.springframework.boot.autoconfigure.cache;

import javax.cache.CacheManager;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/cache/JCacheManagerCustomizer.class */
public interface JCacheManagerCustomizer {
    void customize(CacheManager cacheManager);
}
