package com.jayway.jsonpath;

import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.ParseContextImpl;
import com.jayway.jsonpath.internal.Path;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.Utils;
import com.jayway.jsonpath.internal.path.PathCompiler;
import com.jayway.jsonpath.spi.json.JsonProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/JsonPath.class */
public class JsonPath {
    private final Path path;

    private JsonPath(String jsonPath, Predicate[] filters) {
        Utils.notNull(jsonPath, "path can not be null", new Object[0]);
        this.path = PathCompiler.compile(jsonPath, filters);
    }

    public String getPath() {
        return this.path.toString();
    }

    public static boolean isPathDefinite(String path) {
        return compile(path, new Predicate[0]).isDefinite();
    }

    public boolean isDefinite() {
        return this.path.isDefinite();
    }

    public <T> T read(Object obj) {
        return (T) read(obj, Configuration.defaultConfiguration());
    }

    public <T> T read(Object obj, Configuration configuration) {
        boolean zContainsOption = configuration.containsOption(Option.AS_PATH_LIST);
        boolean zContainsOption2 = configuration.containsOption(Option.ALWAYS_RETURN_LIST);
        boolean zContainsOption3 = configuration.containsOption(Option.SUPPRESS_EXCEPTIONS);
        try {
            if (this.path.isFunctionPath()) {
                if (zContainsOption || zContainsOption2) {
                    throw new JsonPathException("Options " + Option.AS_PATH_LIST + " and " + Option.ALWAYS_RETURN_LIST + " are not allowed when using path functions!");
                }
                return (T) this.path.evaluate(obj, obj, configuration).getValue(true);
            }
            if (zContainsOption) {
                return (T) this.path.evaluate(obj, obj, configuration).getPath();
            }
            T t = (T) this.path.evaluate(obj, obj, configuration).getValue(false);
            if (zContainsOption2 && this.path.isDefinite()) {
                T t2 = (T) configuration.jsonProvider().createArray();
                configuration.jsonProvider().setArrayIndex(t2, 0, t);
                return t2;
            }
            return t;
        } catch (RuntimeException e) {
            if (!zContainsOption3) {
                throw e;
            }
            if (zContainsOption) {
                return (T) configuration.jsonProvider().createArray();
            }
            if (zContainsOption2) {
                return (T) configuration.jsonProvider().createArray();
            }
            if (this.path.isDefinite()) {
                return null;
            }
            return (T) configuration.jsonProvider().createArray();
        }
    }

    public <T> T set(Object obj, Object obj2, Configuration configuration) {
        Utils.notNull(obj, "json can not be null", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        EvaluationContext evaluationContextEvaluate = this.path.evaluate(obj, obj, configuration, true);
        Iterator<PathRef> it = evaluationContextEvaluate.updateOperations().iterator();
        while (it.hasNext()) {
            it.next().set(obj2, configuration);
        }
        return (T) resultByConfiguration(obj, configuration, evaluationContextEvaluate);
    }

    public <T> T map(Object obj, MapFunction mapFunction, Configuration configuration) {
        Utils.notNull(obj, "json can not be null", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        Utils.notNull(mapFunction, "mapFunction can not be null", new Object[0]);
        boolean zContainsOption = configuration.containsOption(Option.AS_PATH_LIST);
        boolean zContainsOption2 = configuration.containsOption(Option.ALWAYS_RETURN_LIST);
        boolean zContainsOption3 = configuration.containsOption(Option.SUPPRESS_EXCEPTIONS);
        try {
            EvaluationContext evaluationContextEvaluate = this.path.evaluate(obj, obj, configuration, true);
            Iterator<PathRef> it = evaluationContextEvaluate.updateOperations().iterator();
            while (it.hasNext()) {
                it.next().convert(mapFunction, configuration);
            }
            return (T) resultByConfiguration(obj, configuration, evaluationContextEvaluate);
        } catch (RuntimeException e) {
            if (!zContainsOption3) {
                throw e;
            }
            if (zContainsOption) {
                return (T) configuration.jsonProvider().createArray();
            }
            if (zContainsOption2) {
                return (T) configuration.jsonProvider().createArray();
            }
            if (this.path.isDefinite()) {
                return null;
            }
            return (T) configuration.jsonProvider().createArray();
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.ArrayList, java.util.List] */
    public <T> T delete(Object obj, Configuration configuration) {
        Utils.notNull(obj, "json can not be null", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        boolean zContainsOption = configuration.containsOption(Option.SUPPRESS_EXCEPTIONS);
        try {
            EvaluationContext evaluationContextEvaluate = this.path.evaluate(obj, obj, configuration, true);
            Iterator<PathRef> it = evaluationContextEvaluate.updateOperations().iterator();
            while (it.hasNext()) {
                it.next().delete(configuration);
            }
            return (T) resultByConfiguration(obj, configuration, evaluationContextEvaluate);
        } catch (RuntimeException e) {
            if (!zContainsOption) {
                throw e;
            }
            ?? r0 = (T) new ArrayList();
            r0.add("delete throws " + e.getMessage());
            return r0;
        }
    }

    public <T> T add(Object obj, Object obj2, Configuration configuration) {
        Utils.notNull(obj, "json can not be null", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        EvaluationContext evaluationContextEvaluate = this.path.evaluate(obj, obj, configuration, true);
        Iterator<PathRef> it = evaluationContextEvaluate.updateOperations().iterator();
        while (it.hasNext()) {
            it.next().add(obj2, configuration);
        }
        return (T) resultByConfiguration(obj, configuration, evaluationContextEvaluate);
    }

    public <T> T put(Object obj, String str, Object obj2, Configuration configuration) {
        Utils.notNull(obj, "json can not be null", new Object[0]);
        Utils.notEmpty(str, "key can not be null or empty", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        EvaluationContext evaluationContextEvaluate = this.path.evaluate(obj, obj, configuration, true);
        Iterator<PathRef> it = evaluationContextEvaluate.updateOperations().iterator();
        while (it.hasNext()) {
            it.next().put(str, obj2, configuration);
        }
        return (T) resultByConfiguration(obj, configuration, evaluationContextEvaluate);
    }

    public <T> T renameKey(Object obj, String str, String str2, Configuration configuration) {
        Utils.notNull(obj, "json can not be null", new Object[0]);
        Utils.notEmpty(str2, "newKeyName can not be null or empty", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        EvaluationContext evaluationContextEvaluate = this.path.evaluate(obj, obj, configuration, true);
        for (PathRef pathRef : evaluationContextEvaluate.updateOperations()) {
            boolean zContainsOption = configuration.containsOption(Option.SUPPRESS_EXCEPTIONS);
            try {
                pathRef.renameKey(str, str2, configuration);
            } catch (RuntimeException e) {
                if (!zContainsOption) {
                    throw e;
                }
            }
        }
        return (T) resultByConfiguration(obj, configuration, evaluationContextEvaluate);
    }

    public <T> T read(String str) {
        return (T) read(str, Configuration.defaultConfiguration());
    }

    public <T> T read(String str, Configuration configuration) {
        Utils.notEmpty(str, "json can not be null or empty", new Object[0]);
        Utils.notNull(configuration, "jsonProvider can not be null", new Object[0]);
        return (T) read(configuration.jsonProvider().parse(str), configuration);
    }

    public <T> T read(URL url) throws IOException {
        return (T) read(url, Configuration.defaultConfiguration());
    }

    public <T> T read(File file) throws IOException {
        return (T) read(file, Configuration.defaultConfiguration());
    }

    public <T> T read(File file, Configuration configuration) throws IOException {
        Utils.notNull(file, "json file can not be null", new Object[0]);
        Utils.isTrue(file.exists(), "json file does not exist");
        Utils.notNull(configuration, "jsonProvider can not be null", new Object[0]);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            T t = (T) read((InputStream) fileInputStream, configuration);
            Utils.closeQuietly(fileInputStream);
            return t;
        } catch (Throwable th) {
            Utils.closeQuietly(fileInputStream);
            throw th;
        }
    }

    public <T> T read(InputStream inputStream) throws IOException {
        return (T) read(inputStream, Configuration.defaultConfiguration());
    }

    public <T> T read(InputStream inputStream, Configuration configuration) throws IOException {
        Utils.notNull(inputStream, "json input stream can not be null", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        return (T) read(inputStream, "UTF-8", configuration);
    }

    public <T> T read(InputStream inputStream, String str, Configuration configuration) throws IOException {
        Utils.notNull(inputStream, "json input stream can not be null", new Object[0]);
        Utils.notNull(str, "charset can not be null", new Object[0]);
        Utils.notNull(configuration, "configuration can not be null", new Object[0]);
        try {
            T t = (T) read(configuration.jsonProvider().parse(inputStream, str), configuration);
            Utils.closeQuietly(inputStream);
            return t;
        } catch (Throwable th) {
            Utils.closeQuietly(inputStream);
            throw th;
        }
    }

    public static JsonPath compile(String jsonPath, Predicate... filters) {
        Utils.notEmpty(jsonPath, "json can not be null or empty", new Object[0]);
        return new JsonPath(jsonPath, filters);
    }

    public static <T> T read(Object obj, String str, Predicate... predicateArr) {
        return (T) parse(obj).read(str, predicateArr);
    }

    public static <T> T read(String str, String str2, Predicate... predicateArr) {
        return (T) new ParseContextImpl().parse(str).read(str2, predicateArr);
    }

    @Deprecated
    public static <T> T read(URL url, String str, Predicate... predicateArr) throws IOException {
        return (T) new ParseContextImpl().parse(url).read(str, predicateArr);
    }

    public static <T> T read(File file, String str, Predicate... predicateArr) throws IOException {
        return (T) new ParseContextImpl().parse(file).read(str, predicateArr);
    }

    public static <T> T read(InputStream inputStream, String str, Predicate... predicateArr) throws IOException {
        return (T) new ParseContextImpl().parse(inputStream).read(str, predicateArr);
    }

    public static ParseContext using(Configuration configuration) {
        return new ParseContextImpl(configuration);
    }

    @Deprecated
    public static ParseContext using(JsonProvider provider) {
        return new ParseContextImpl(Configuration.builder().jsonProvider(provider).build());
    }

    public static DocumentContext parse(Object json) {
        return new ParseContextImpl().parse(json);
    }

    public static DocumentContext parse(String json) {
        return new ParseContextImpl().parse(json);
    }

    public static DocumentContext parse(InputStream json) {
        return new ParseContextImpl().parse(json);
    }

    public static DocumentContext parse(File json) throws IOException {
        return new ParseContextImpl().parse(json);
    }

    @Deprecated
    public static DocumentContext parse(URL json) throws IOException {
        return new ParseContextImpl().parse(json);
    }

    public static DocumentContext parse(Object json, Configuration configuration) {
        return new ParseContextImpl(configuration).parse(json);
    }

    public static DocumentContext parse(String json, Configuration configuration) {
        return new ParseContextImpl(configuration).parse(json);
    }

    public static DocumentContext parse(InputStream json, Configuration configuration) {
        return new ParseContextImpl(configuration).parse(json);
    }

    public static DocumentContext parse(File json, Configuration configuration) throws IOException {
        return new ParseContextImpl(configuration).parse(json);
    }

    @Deprecated
    public static DocumentContext parse(URL json, Configuration configuration) throws IOException {
        return new ParseContextImpl(configuration).parse(json);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T resultByConfiguration(Object obj, Configuration configuration, EvaluationContext evaluationContext) {
        if (configuration.containsOption(Option.AS_PATH_LIST)) {
            return (T) evaluationContext.getPathList();
        }
        return obj;
    }
}
