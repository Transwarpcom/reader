package org.springframework.cache.interceptor;

import java.util.Collection;
import org.springframework.cache.Cache;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/cache/interceptor/CacheResolver.class */
public interface CacheResolver {
    Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> cacheOperationInvocationContext);
}
