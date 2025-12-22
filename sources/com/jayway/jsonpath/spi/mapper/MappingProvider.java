package com.jayway.jsonpath.spi.mapper;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.TypeRef;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/MappingProvider.class */
public interface MappingProvider {
    <T> T map(Object obj, Class<T> cls, Configuration configuration);

    <T> T map(Object obj, TypeRef<T> typeRef, Configuration configuration);
}
