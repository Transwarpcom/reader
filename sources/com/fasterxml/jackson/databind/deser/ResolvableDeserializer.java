package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-databind-2.9.9.jar:com/fasterxml/jackson/databind/deser/ResolvableDeserializer.class */
public interface ResolvableDeserializer {
    void resolve(DeserializationContext deserializationContext) throws JsonMappingException;
}
