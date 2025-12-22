package com.jayway.jsonpath.spi.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.TypeRef;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JacksonMappingProvider.class */
public class JacksonMappingProvider implements MappingProvider {
    private final ObjectMapper objectMapper;

    public JacksonMappingProvider() {
        this(new ObjectMapper());
    }

    public JacksonMappingProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object obj, Class<T> cls, Configuration configuration) {
        if (obj == null) {
            return null;
        }
        try {
            return (T) this.objectMapper.convertValue(obj, cls);
        } catch (Exception e) {
            throw new MappingException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object obj, TypeRef<T> typeRef, Configuration configuration) {
        if (obj == null) {
            return null;
        }
        try {
            return (T) this.objectMapper.convertValue(obj, this.objectMapper.getTypeFactory().constructType(typeRef.getType()));
        } catch (Exception e) {
            throw new MappingException(e);
        }
    }
}
