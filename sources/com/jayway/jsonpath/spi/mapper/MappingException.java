package com.jayway.jsonpath.spi.mapper;

import com.jayway.jsonpath.JsonPathException;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/MappingException.class */
public class MappingException extends JsonPathException {
    public MappingException(Throwable cause) {
        super(cause);
    }

    public MappingException(String message) {
        super(message);
    }
}
