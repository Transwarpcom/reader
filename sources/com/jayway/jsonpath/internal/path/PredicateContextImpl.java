package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.Path;
import com.jayway.jsonpath.spi.mapper.MappingException;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/PredicateContextImpl.class */
public class PredicateContextImpl implements Predicate.PredicateContext {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) PredicateContextImpl.class);
    private final Object contextDocument;
    private final Object rootDocument;
    private final Configuration configuration;
    private final HashMap<Path, Object> documentPathCache;

    public PredicateContextImpl(Object contextDocument, Object rootDocument, Configuration configuration, HashMap<Path, Object> documentPathCache) {
        this.contextDocument = contextDocument;
        this.rootDocument = rootDocument;
        this.configuration = configuration;
        this.documentPathCache = documentPathCache;
    }

    public Object evaluate(Path path) {
        Object result;
        if (path.isRootPath()) {
            if (this.documentPathCache.containsKey(path)) {
                logger.debug("Using cached result for root path: " + path.toString());
                result = this.documentPathCache.get(path);
            } else {
                result = path.evaluate(this.rootDocument, this.rootDocument, this.configuration).getValue();
                this.documentPathCache.put(path, result);
            }
        } else {
            result = path.evaluate(this.contextDocument, this.rootDocument, this.configuration).getValue();
        }
        return result;
    }

    public HashMap<Path, Object> documentPathCache() {
        return this.documentPathCache;
    }

    @Override // com.jayway.jsonpath.Predicate.PredicateContext
    public Object item() {
        return this.contextDocument;
    }

    @Override // com.jayway.jsonpath.Predicate.PredicateContext
    public <T> T item(Class<T> cls) throws MappingException {
        return (T) configuration().mappingProvider().map(this.contextDocument, cls, this.configuration);
    }

    @Override // com.jayway.jsonpath.Predicate.PredicateContext
    public Object root() {
        return this.rootDocument;
    }

    @Override // com.jayway.jsonpath.Predicate.PredicateContext
    public Configuration configuration() {
        return this.configuration;
    }
}
