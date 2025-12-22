package io.vertx.ext.web.templ;

import io.vertx.ext.web.common.WebEnvironment;
import io.vertx.ext.web.impl.ConcurrentLRUCache;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/templ/CachingTemplateEngine.class */
public abstract class CachingTemplateEngine<T> implements TemplateEngine {
    private final boolean enableCache;
    protected final ConcurrentLRUCache<String, T> cache;
    protected String extension;

    protected CachingTemplateEngine(String ext, int maxCacheSize) {
        this.enableCache = !WebEnvironment.development();
        Objects.requireNonNull(ext);
        if (maxCacheSize < 1) {
            throw new IllegalArgumentException("maxCacheSize must be >= 1");
        }
        doSetExtension(ext);
        this.cache = new ConcurrentLRUCache<>(maxCacheSize);
    }

    @Override // io.vertx.ext.web.templ.TemplateEngine, io.vertx.ext.web.common.template.TemplateEngine
    public boolean isCachingEnabled() {
        return this.enableCache;
    }

    protected String adjustLocation(String location) {
        if (!location.endsWith(this.extension)) {
            location = location + this.extension;
        }
        return location;
    }

    protected void doSetExtension(String ext) {
        this.extension = ext.charAt(0) == '.' ? ext : "." + ext;
    }
}
