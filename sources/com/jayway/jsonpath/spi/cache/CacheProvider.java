package com.jayway.jsonpath.spi.cache;

import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.internal.Utils;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/cache/CacheProvider.class */
public class CacheProvider {
    private static Cache cache;

    public static void setCache(Cache cache2) {
        Utils.notNull(cache2, "Cache may not be null", new Object[0]);
        synchronized (CacheProvider.class) {
            if (cache != null) {
                throw new JsonPathException("Cache provider must be configured before cache is accessed.");
            }
            cache = cache2;
        }
    }

    public static Cache getCache() {
        if (cache == null) {
            synchronized (CacheProvider.class) {
                if (cache == null) {
                    cache = getDefaultCache();
                }
            }
        }
        return cache;
    }

    private static Cache getDefaultCache() {
        return new LRUCache(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
    }
}
