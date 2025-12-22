package com.jayway.jsonpath.internal;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.ParseContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/ParseContextImpl.class */
public class ParseContextImpl implements ParseContext {
    private final Configuration configuration;

    public ParseContextImpl() {
        this(Configuration.defaultConfiguration());
    }

    public ParseContextImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override // com.jayway.jsonpath.ParseContext
    public DocumentContext parse(Object json) {
        Utils.notNull(json, "json object can not be null", new Object[0]);
        return new JsonContext(json, this.configuration);
    }

    @Override // com.jayway.jsonpath.ParseContext
    public DocumentContext parse(String json) throws InvalidJsonException {
        Utils.notEmpty(json, "json string can not be null or empty", new Object[0]);
        Object obj = this.configuration.jsonProvider().parse(json);
        return new JsonContext(obj, this.configuration);
    }

    @Override // com.jayway.jsonpath.ParseContext
    public DocumentContext parse(InputStream json) {
        return parse(json, "UTF-8");
    }

    @Override // com.jayway.jsonpath.ParseContext
    public DocumentContext parse(InputStream json, String charset) throws IOException {
        Utils.notNull(json, "json input stream can not be null", new Object[0]);
        Utils.notNull(charset, "charset can not be null", new Object[0]);
        try {
            Object obj = this.configuration.jsonProvider().parse(json, charset);
            JsonContext jsonContext = new JsonContext(obj, this.configuration);
            Utils.closeQuietly(json);
            return jsonContext;
        } catch (Throwable th) {
            Utils.closeQuietly(json);
            throw th;
        }
    }

    @Override // com.jayway.jsonpath.ParseContext
    public DocumentContext parse(File json) throws IOException {
        Utils.notNull(json, "json file can not be null", new Object[0]);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(json);
            DocumentContext documentContext = parse((InputStream) fis);
            Utils.closeQuietly(fis);
            return documentContext;
        } catch (Throwable th) {
            Utils.closeQuietly(fis);
            throw th;
        }
    }

    @Override // com.jayway.jsonpath.ParseContext
    @Deprecated
    public DocumentContext parse(URL url) throws IOException {
        Utils.notNull(url, "url can not be null", new Object[0]);
        InputStream fis = null;
        try {
            fis = url.openStream();
            DocumentContext documentContext = parse(fis);
            Utils.closeQuietly(fis);
            return documentContext;
        } catch (Throwable th) {
            Utils.closeQuietly(fis);
            throw th;
        }
    }
}
