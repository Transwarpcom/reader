package com.jayway.jsonpath;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/WriteContext.class */
public interface WriteContext {
    Configuration configuration();

    <T> T json();

    String jsonString();

    DocumentContext set(String str, Object obj, Predicate... predicateArr);

    DocumentContext set(JsonPath jsonPath, Object obj);

    DocumentContext map(String str, MapFunction mapFunction, Predicate... predicateArr);

    DocumentContext map(JsonPath jsonPath, MapFunction mapFunction);

    DocumentContext delete(String str, Predicate... predicateArr);

    DocumentContext delete(JsonPath jsonPath);

    DocumentContext add(String str, Object obj, Predicate... predicateArr);

    DocumentContext add(JsonPath jsonPath, Object obj);

    DocumentContext put(String str, String str2, Object obj, Predicate... predicateArr);

    DocumentContext put(JsonPath jsonPath, String str, Object obj);

    DocumentContext renameKey(String str, String str2, String str3, Predicate... predicateArr);

    DocumentContext renameKey(JsonPath jsonPath, String str, String str2);
}
