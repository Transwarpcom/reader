package com.jayway.jsonpath.spi.cache;

import com.jayway.jsonpath.JsonPath;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/cache/Cache.class */
public interface Cache {
    JsonPath get(String str);

    void put(String str, JsonPath jsonPath);
}
