package org.springframework.cache.interceptor;

import java.util.Collection;
import org.springframework.cache.CacheManager;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/cache/interceptor/SimpleCacheResolver.class */
public class SimpleCacheResolver extends AbstractCacheResolver {
    public SimpleCacheResolver() {
    }

    public SimpleCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override // org.springframework.cache.interceptor.AbstractCacheResolver
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }

    @Nullable
    static SimpleCacheResolver of(@Nullable CacheManager cacheManager) {
        if (cacheManager != null) {
            return new SimpleCacheResolver(cacheManager);
        }
        return null;
    }
}
