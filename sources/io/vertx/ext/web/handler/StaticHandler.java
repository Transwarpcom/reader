package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.Http2PushMapping;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.common.WebEnvironment;
import io.vertx.ext.web.handler.impl.StaticHandlerImpl;
import java.util.List;
import java.util.Set;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/StaticHandler.class */
public interface StaticHandler extends Handler<RoutingContext> {
    public static final String DEFAULT_WEB_ROOT = "webroot";
    public static final boolean DEFAULT_FILES_READ_ONLY = true;
    public static final long DEFAULT_MAX_AGE_SECONDS = 86400;
    public static final boolean DEFAULT_CACHING_ENABLED;
    public static final boolean DEFAULT_DIRECTORY_LISTING = false;
    public static final String DEFAULT_DIRECTORY_TEMPLATE = "META-INF/vertx/web/vertx-web-directory.html";
    public static final boolean DEFAULT_INCLUDE_HIDDEN = true;
    public static final long DEFAULT_CACHE_ENTRY_TIMEOUT = 30000;
    public static final String DEFAULT_INDEX_PAGE = "/index.html";
    public static final int DEFAULT_MAX_CACHE_SIZE = 10000;
    public static final boolean DEFAULT_ALWAYS_ASYNC_FS = false;
    public static final boolean DEFAULT_ENABLE_FS_TUNING = true;
    public static final long DEFAULT_MAX_AVG_SERVE_TIME_NS = 1000000;
    public static final boolean DEFAULT_RANGE_SUPPORT = true;
    public static final boolean DEFAULT_ROOT_FILESYSTEM_ACCESS = false;
    public static final boolean DEFAULT_SEND_VARY_HEADER = true;

    @Fluent
    StaticHandler setAllowRootFileSystemAccess(boolean z);

    @Fluent
    StaticHandler setWebRoot(String str);

    @Fluent
    StaticHandler setFilesReadOnly(boolean z);

    @Fluent
    StaticHandler setMaxAgeSeconds(long j);

    @Fluent
    StaticHandler setCachingEnabled(boolean z);

    @Fluent
    StaticHandler setDirectoryListing(boolean z);

    @Fluent
    StaticHandler setIncludeHidden(boolean z);

    @Fluent
    StaticHandler setCacheEntryTimeout(long j);

    @Fluent
    StaticHandler setIndexPage(String str);

    @Fluent
    StaticHandler setMaxCacheSize(int i);

    @Fluent
    StaticHandler setHttp2PushMapping(List<Http2PushMapping> list);

    @Fluent
    StaticHandler skipCompressionForMediaTypes(Set<String> set);

    @Fluent
    StaticHandler skipCompressionForSuffixes(Set<String> set);

    @Fluent
    StaticHandler setAlwaysAsyncFS(boolean z);

    @Fluent
    StaticHandler setEnableFSTuning(boolean z);

    @Fluent
    StaticHandler setMaxAvgServeTimeNs(long j);

    @Fluent
    StaticHandler setDirectoryTemplate(String str);

    @Fluent
    StaticHandler setEnableRangeSupport(boolean z);

    @Fluent
    StaticHandler setSendVaryHeader(boolean z);

    @Fluent
    StaticHandler setDefaultContentEncoding(String str);

    static {
        DEFAULT_CACHING_ENABLED = !WebEnvironment.development();
    }

    static StaticHandler create() {
        return create(null);
    }

    static StaticHandler create(String root) {
        return new StaticHandlerImpl(root);
    }

    @GenIgnore
    @Deprecated
    static StaticHandler create(String root, ClassLoader classLoader) {
        return new StaticHandlerImpl(root, classLoader);
    }
}
