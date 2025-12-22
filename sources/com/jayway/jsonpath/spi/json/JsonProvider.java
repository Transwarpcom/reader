package com.jayway.jsonpath.spi.json;

import com.jayway.jsonpath.InvalidJsonException;
import java.io.InputStream;
import java.util.Collection;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JsonProvider.class */
public interface JsonProvider {
    public static final Object UNDEFINED = new Object();

    Object parse(String str) throws InvalidJsonException;

    Object parse(InputStream inputStream, String str) throws InvalidJsonException;

    String toJson(Object obj);

    Object createArray();

    Object createMap();

    boolean isArray(Object obj);

    int length(Object obj);

    Iterable<?> toIterable(Object obj);

    Collection<String> getPropertyKeys(Object obj);

    Object getArrayIndex(Object obj, int i);

    @Deprecated
    Object getArrayIndex(Object obj, int i, boolean z);

    void setArrayIndex(Object obj, int i, Object obj2);

    Object getMapValue(Object obj, String str);

    void setProperty(Object obj, Object obj2, Object obj3);

    void removeProperty(Object obj, Object obj2);

    boolean isMap(Object obj);

    Object unwrap(Object obj);
}
