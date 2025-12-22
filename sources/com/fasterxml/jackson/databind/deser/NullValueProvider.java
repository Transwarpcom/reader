package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.AccessPattern;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-databind-2.9.9.jar:com/fasterxml/jackson/databind/deser/NullValueProvider.class */
public interface NullValueProvider {
    Object getNullValue(DeserializationContext deserializationContext) throws JsonMappingException;

    AccessPattern getNullAccessPattern();
}
