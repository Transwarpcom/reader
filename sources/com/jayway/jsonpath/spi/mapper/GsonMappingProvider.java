package com.jayway.jsonpath.spi.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.TypeRef;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/GsonMappingProvider.class */
public class GsonMappingProvider implements MappingProvider {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) GsonMappingProvider.class);
    private final Callable<Gson> factory;

    public GsonMappingProvider(final Gson gson) {
        this(new Callable<Gson>() { // from class: com.jayway.jsonpath.spi.mapper.GsonMappingProvider.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Gson call() {
                return gson;
            }
        });
    }

    public GsonMappingProvider(Callable<Gson> factory) {
        this.factory = factory;
    }

    public GsonMappingProvider() throws ClassNotFoundException {
        try {
            Class.forName("com.google.gson.Gson");
            this.factory = new Callable<Gson>() { // from class: com.jayway.jsonpath.spi.mapper.GsonMappingProvider.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Gson call() {
                    return new Gson();
                }
            };
        } catch (ClassNotFoundException e) {
            logger.error("Gson not found on class path. No converters configured.");
            throw new JsonPathException("Gson not found on path", e);
        }
    }

    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object source, Class<T> targetType, Configuration configuration) {
        if (source == null) {
            return null;
        }
        try {
            return this.factory.call().getAdapter(targetType).fromJsonTree((JsonElement) source);
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
            return this.factory.call().getAdapter(TypeToken.get(typeRef.getType())).fromJsonTree((JsonElement) obj);
        } catch (Exception e) {
            throw new MappingException(e);
        }
    }
}
