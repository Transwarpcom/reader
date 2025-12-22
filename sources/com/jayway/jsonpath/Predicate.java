package com.jayway.jsonpath;

import com.jayway.jsonpath.spi.mapper.MappingException;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/Predicate.class */
public interface Predicate {

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/Predicate$PredicateContext.class */
    public interface PredicateContext {
        Object item();

        <T> T item(Class<T> cls) throws MappingException;

        Object root();

        Configuration configuration();
    }

    boolean apply(PredicateContext predicateContext);
}
