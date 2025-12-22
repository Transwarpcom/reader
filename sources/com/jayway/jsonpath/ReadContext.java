package com.jayway.jsonpath;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/ReadContext.class */
public interface ReadContext {
    Configuration configuration();

    <T> T json();

    String jsonString();

    <T> T read(String str, Predicate... predicateArr);

    <T> T read(String str, Class<T> cls, Predicate... predicateArr);

    <T> T read(JsonPath jsonPath);

    <T> T read(JsonPath jsonPath, Class<T> cls);

    <T> T read(JsonPath jsonPath, TypeRef<T> typeRef);

    <T> T read(String str, TypeRef<T> typeRef);

    ReadContext limit(int i);

    ReadContext withListeners(EvaluationListener... evaluationListenerArr);
}
