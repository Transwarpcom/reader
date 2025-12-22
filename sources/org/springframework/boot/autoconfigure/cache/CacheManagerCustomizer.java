package org.springframework.boot.autoconfigure.cache;

import org.springframework.cache.CacheManager;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/cache/CacheManagerCustomizer.class */
public interface CacheManagerCustomizer<T extends CacheManager> {
    void customize(T cacheManager);
}
