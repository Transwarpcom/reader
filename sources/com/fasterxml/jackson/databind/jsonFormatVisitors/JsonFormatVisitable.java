package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-databind-2.9.9.jar:com/fasterxml/jackson/databind/jsonFormatVisitors/JsonFormatVisitable.class */
public interface JsonFormatVisitable {
    void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException;
}
