package com.jayway.jsonpath.spi.cache;

import com.jayway.jsonpath.JsonPath;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/cache/NOOPCache.class */
public class NOOPCache implements Cache {
    @Override // com.jayway.jsonpath.spi.cache.Cache
    public JsonPath get(String key) {
        return null;
    }

    @Override // com.jayway.jsonpath.spi.cache.Cache
    public void put(String key, JsonPath value) {
    }
}
