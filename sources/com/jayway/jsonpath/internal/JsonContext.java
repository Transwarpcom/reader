package com.jayway.jsonpath.internal;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.EvaluationListener;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.MapFunction;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.cache.Cache;
import com.jayway.jsonpath.spi.cache.CacheProvider;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/JsonContext.class */
public class JsonContext implements DocumentContext {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) JsonContext.class);
    private final Configuration configuration;
    private final Object json;

    JsonContext(Object json, Configuration configuration) {
        Utils.notNull(json, "json can not be null", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        this.configuration = configuration;
        this.json = json;
    }

    @Override // com.jayway.jsonpath.ReadContext, com.jayway.jsonpath.WriteContext
    public Configuration configuration() {
        return this.configuration;
    }

    @Override // com.jayway.jsonpath.ReadContext, com.jayway.jsonpath.WriteContext
    public Object json() {
        return this.json;
    }

    @Override // com.jayway.jsonpath.ReadContext, com.jayway.jsonpath.WriteContext
    public String jsonString() {
        return this.configuration.jsonProvider().toJson(this.json);
    }

    @Override // com.jayway.jsonpath.ReadContext
    public <T> T read(String str, Predicate... predicateArr) {
        Utils.notEmpty(str, "path can not be null or empty", new Object[0]);
        return (T) read(pathFromCache(str, predicateArr));
    }

    @Override // com.jayway.jsonpath.ReadContext
    public <T> T read(String str, Class<T> cls, Predicate... predicateArr) {
        return (T) convert(read(str, predicateArr), cls, this.configuration);
    }

    @Override // com.jayway.jsonpath.ReadContext
    public <T> T read(JsonPath jsonPath) {
        Utils.notNull(jsonPath, "path can not be null", new Object[0]);
        return (T) jsonPath.read(this.json, this.configuration);
    }

    @Override // com.jayway.jsonpath.ReadContext
    public <T> T read(JsonPath jsonPath, Class<T> cls) {
        return (T) convert(read(jsonPath), cls, this.configuration);
    }

    @Override // com.jayway.jsonpath.ReadContext
    public <T> T read(JsonPath jsonPath, TypeRef<T> typeRef) {
        return (T) convert(read(jsonPath), typeRef, this.configuration);
    }

    @Override // com.jayway.jsonpath.ReadContext
    public <T> T read(String str, TypeRef<T> typeRef) {
        return (T) convert(read(str, new Predicate[0]), typeRef, this.configuration);
    }

    @Override // com.jayway.jsonpath.ReadContext
    public ReadContext limit(int maxResults) {
        return withListeners(new LimitingEvaluationListener(maxResults));
    }

    @Override // com.jayway.jsonpath.ReadContext
    public ReadContext withListeners(EvaluationListener... listener) {
        return new JsonContext(this.json, this.configuration.setEvaluationListeners(listener));
    }

    private <T> T convert(Object obj, Class<T> cls, Configuration configuration) {
        return (T) configuration.mappingProvider().map(obj, cls, configuration);
    }

    private <T> T convert(Object obj, TypeRef<T> typeRef, Configuration configuration) {
        return (T) configuration.mappingProvider().map(obj, typeRef, configuration);
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext set(String path, Object newValue, Predicate... filters) {
        return set(pathFromCache(path, filters), newValue);
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext set(JsonPath path, Object newValue) {
        List<String> modified = (List) path.set(this.json, newValue, this.configuration.addOptions(Option.AS_PATH_LIST));
        if (logger.isDebugEnabled()) {
            for (String p : modified) {
                logger.debug("Set path {} new value {}", p, newValue);
            }
        }
        return this;
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext map(String path, MapFunction mapFunction, Predicate... filters) {
        map(pathFromCache(path, filters), mapFunction);
        return this;
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext map(JsonPath path, MapFunction mapFunction) {
        Object obj = path.map(this.json, mapFunction, this.configuration);
        if (obj == null) {
            return null;
        }
        return this;
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext delete(String path, Predicate... filters) {
        return delete(pathFromCache(path, filters));
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext delete(JsonPath path) {
        List<String> modified = (List) path.delete(this.json, this.configuration.addOptions(Option.AS_PATH_LIST));
        if (logger.isDebugEnabled()) {
            for (String p : modified) {
                logger.debug("Delete path {}", p);
            }
        }
        return this;
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext add(String path, Object value, Predicate... filters) {
        return add(pathFromCache(path, filters), value);
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext add(JsonPath path, Object value) {
        List<String> modified = (List) path.add(this.json, value, this.configuration.addOptions(Option.AS_PATH_LIST));
        if (logger.isDebugEnabled()) {
            for (String p : modified) {
                logger.debug("Add path {} new value {}", p, value);
            }
        }
        return this;
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext put(String path, String key, Object value, Predicate... filters) {
        return put(pathFromCache(path, filters), key, value);
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext renameKey(String path, String oldKeyName, String newKeyName, Predicate... filters) {
        return renameKey(pathFromCache(path, filters), oldKeyName, newKeyName);
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext renameKey(JsonPath path, String oldKeyName, String newKeyName) {
        List<String> modified = (List) path.renameKey(this.json, oldKeyName, newKeyName, this.configuration.addOptions(Option.AS_PATH_LIST));
        if (logger.isDebugEnabled()) {
            for (String p : modified) {
                logger.debug("Rename path {} new value {}", p, newKeyName);
            }
        }
        return this;
    }

    @Override // com.jayway.jsonpath.WriteContext
    public DocumentContext put(JsonPath path, String key, Object value) {
        List<String> modified = (List) path.put(this.json, key, value, this.configuration.addOptions(Option.AS_PATH_LIST));
        if (logger.isDebugEnabled()) {
            for (String p : modified) {
                logger.debug("Put path {} key {} value {}", p, key, value);
            }
        }
        return this;
    }

    private JsonPath pathFromCache(String path, Predicate[] filters) {
        Cache cache = CacheProvider.getCache();
        String cacheKey = Utils.concat(path, new LinkedList(Arrays.asList(filters)).toString());
        JsonPath jsonPath = cache.get(cacheKey);
        if (jsonPath == null) {
            jsonPath = JsonPath.compile(path, filters);
            cache.put(cacheKey, jsonPath);
        }
        return jsonPath;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/JsonContext$LimitingEvaluationListener.class */
    private static final class LimitingEvaluationListener implements EvaluationListener {
        final int limit;

        private LimitingEvaluationListener(int limit) {
            this.limit = limit;
        }

        @Override // com.jayway.jsonpath.EvaluationListener
        public EvaluationListener.EvaluationContinuation resultFound(EvaluationListener.FoundResult found) {
            if (found.index() == this.limit - 1) {
                return EvaluationListener.EvaluationContinuation.ABORT;
            }
            return EvaluationListener.EvaluationContinuation.CONTINUE;
        }
    }
}
