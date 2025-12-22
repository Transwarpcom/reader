package org.springframework.cache;

import java.util.Collection;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/cache/CacheManager.class */
public interface CacheManager {
    @Nullable
    Cache getCache(String str);

    Collection<String> getCacheNames();
}
