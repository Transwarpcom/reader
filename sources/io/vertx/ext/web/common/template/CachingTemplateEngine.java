package io.vertx.ext.web.common.template;

import io.vertx.ext.web.common.WebEnvironment;
import io.vertx.ext.web.common.template.impl.ConcurrentLRUCache;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/common/template/CachingTemplateEngine.class */
public abstract class CachingTemplateEngine<T> implements TemplateEngine {
    private final boolean enableCache;
    protected final ConcurrentLRUCache<String, T> cache;

    @Deprecated
    protected String extension;

    @Deprecated
    protected CachingTemplateEngine(String ext, int maxCacheSize) {
        this(maxCacheSize);
        Objects.requireNonNull(ext);
        doSetExtension(ext);
    }

    protected CachingTemplateEngine(int maxCacheSize) {
        this.enableCache = !WebEnvironment.development();
        if (maxCacheSize < 1) {
            throw new IllegalArgumentException("maxCacheSize must be >= 1");
        }
        this.cache = new ConcurrentLRUCache<>(maxCacheSize);
    }

    @Override // io.vertx.ext.web.common.template.TemplateEngine
    public boolean isCachingEnabled() {
        return this.enableCache;
    }

    @Deprecated
    protected String adjustLocation(String location) {
        if (this.extension != null && !location.endsWith(this.extension)) {
            location = location + this.extension;
        }
        return location;
    }

    @Deprecated
    protected void doSetExtension(String ext) {
        this.extension = ext.charAt(0) == '.' ? ext : "." + ext;
    }
}
