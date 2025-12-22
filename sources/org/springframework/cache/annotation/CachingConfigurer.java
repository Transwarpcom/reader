package org.springframework.cache.annotation;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/cache/annotation/CachingConfigurer.class */
public interface CachingConfigurer {
    @Nullable
    CacheManager cacheManager();

    @Nullable
    CacheResolver cacheResolver();

    @Nullable
    KeyGenerator keyGenerator();

    @Nullable
    CacheErrorHandler errorHandler();
}
