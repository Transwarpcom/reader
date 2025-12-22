package io.vertx.ext.web.handler.impl;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.impl.HttpUtils;
import io.vertx.core.http.impl.MimeMapping;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.impl.URIDecoder;
import io.vertx.ext.web.Http2PushMapping;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.impl.LRUCache;
import io.vertx.ext.web.impl.Utils;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1029)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:245)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:160)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:65)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:58)
    */
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/StaticHandlerImpl.class */
public class StaticHandlerImpl implements StaticHandler {
    private String webRoot;
    private long maxAgeSeconds;
    private boolean directoryListing;
    private String directoryTemplateResource;
    private String directoryTemplate;
    private boolean includeHidden;
    private boolean filesReadOnly;
    private String indexPage;
    private List<Http2PushMapping> http2PushMappings;
    private boolean rangeSupport;
    private boolean allowRootFileSystemAccess;
    private boolean sendVaryHeader;
    private String defaultContentEncoding;
    private Set<String> compressedMediaTypes;
    private Set<String> compressedFileSuffixes;
    private final ClassLoader classLoader;
    private final FSTune tune;
    private final FSPropsCache cache;
    private static final Logger log = LoggerFactory.getLogger((Class<?>) StaticHandlerImpl.class);
    private static final Pattern RANGE = Pattern.compile("^bytes=(\\d+)-(\\d*)$");

    @Override // io.vertx.core.Handler
    public /* bridge */ /* synthetic */ void handle(RoutingContext routingContext) {
        handle2(routingContext);
    }

    static {
    }

    @Deprecated
    public StaticHandlerImpl(String root, ClassLoader classLoader) {
        this.webRoot = StaticHandler.DEFAULT_WEB_ROOT;
        this.maxAgeSeconds = 86400L;
        this.directoryListing = false;
        this.directoryTemplateResource = StaticHandler.DEFAULT_DIRECTORY_TEMPLATE;
        this.includeHidden = true;
        this.filesReadOnly = true;
        this.indexPage = StaticHandler.DEFAULT_INDEX_PAGE;
        this.rangeSupport = true;
        this.allowRootFileSystemAccess = false;
        this.sendVaryHeader = true;
        this.defaultContentEncoding = Charset.defaultCharset().name();
        this.compressedMediaTypes = Collections.emptySet();
        this.compressedFileSuffixes = Collections.emptySet();
        this.tune = new FSTune();
        this.cache = new FSPropsCache();
        this.classLoader = classLoader;
        if (root != null) {
            setRoot(root);
        }
    }

    public StaticHandlerImpl(String root) {
        this.webRoot = StaticHandler.DEFAULT_WEB_ROOT;
        this.maxAgeSeconds = 86400L;
        this.directoryListing = false;
        this.directoryTemplateResource = StaticHandler.DEFAULT_DIRECTORY_TEMPLATE;
        this.includeHidden = true;
        this.filesReadOnly = true;
        this.indexPage = StaticHandler.DEFAULT_INDEX_PAGE;
        this.rangeSupport = true;
        this.allowRootFileSystemAccess = false;
        this.sendVaryHeader = true;
        this.defaultContentEncoding = Charset.defaultCharset().name();
        this.compressedMediaTypes = Collections.emptySet();
        this.compressedFileSuffixes = Collections.emptySet();
        this.tune = new FSTune();
        this.cache = new FSPropsCache();
        this.classLoader = null;
        if (root != null) {
            setRoot(root);
        }
    }

    private String directoryTemplate(Vertx vertx) {
        if (this.directoryTemplate == null) {
            this.directoryTemplate = Utils.readFileToString(vertx, this.directoryTemplateResource);
        }
        return this.directoryTemplate;
    }

    private void writeCacheHeaders(HttpServerRequest request, FileProps props) {
        MultiMap headers = request.response().headers();
        if (this.cache.enabled()) {
            Utils.addToMapIfAbsent(headers, "cache-control", "public, max-age=" + this.maxAgeSeconds);
            Utils.addToMapIfAbsent(headers, "last-modified", Utils.formatRFC1123DateTime(props.lastModifiedTime()));
            if (this.sendVaryHeader && request.headers().contains("accept-encoding")) {
                Utils.addToMapIfAbsent(headers, "vary", "accept-encoding");
            }
        }
        headers.set(PackageDocumentBase.DCTags.date, Utils.formatRFC1123DateTime(System.currentTimeMillis()));
    }

    /* renamed from: handle, reason: avoid collision after fix types in other method */
    public void handle2(RoutingContext context) {
        HttpServerRequest request = context.request();
        if (request.method() != HttpMethod.GET && request.method() != HttpMethod.HEAD) {
            if (log.isTraceEnabled()) {
                log.trace("Not GET or HEAD so ignoring request");
            }
            context.next();
            return;
        }
        String path = HttpUtils.removeDots(URIDecoder.decodeURIComponent(context.normalisedPath(), false));
        if (path == null) {
            log.warn("Invalid path: " + context.request().path());
            context.next();
        } else {
            if (!this.directoryListing && "/".equals(path)) {
                path = this.indexPage;
            }
            sendStatic(context, path);
        }
    }

    private void sendStatic(RoutingContext context, String path) {
        String file = null;
        if (!this.includeHidden) {
            file = getFile(path, context);
            int idx = file.lastIndexOf(47);
            String name = file.substring(idx + 1);
            if (name.length() > 0 && name.charAt(0) == '.') {
                context.next();
                return;
            }
        }
        CacheEntry entry = this.cache.get(path);
        if (entry != null && (this.filesReadOnly || !entry.isOutOfDate())) {
            if (entry.isMissing()) {
                context.next();
                return;
            } else if (entry.shouldUseCached(context.request())) {
                context.response().setStatusCode(HttpResponseStatus.NOT_MODIFIED.code()).end();
                return;
            }
        }
        boolean dirty = this.cache.enabled() && entry != null;
        String sfile = file == null ? getFile(path, context) : file;
        isFileExisting(context, sfile, exists -> {
            if (exists.failed()) {
                context.fail(exists.cause());
            } else {
                if (!((Boolean) exists.result()).booleanValue()) {
                    if (this.cache.enabled()) {
                        this.cache.put(path, null);
                    }
                    context.next();
                    return;
                }
                getFileProps(context, sfile, res -> {
                    if (res.succeeded()) {
                        FileProps fprops = (FileProps) res.result();
                        if (fprops == null) {
                            if (dirty) {
                                this.cache.remove(path);
                            }
                            context.next();
                            return;
                        } else if (fprops.isDirectory()) {
                            if (dirty) {
                                this.cache.remove(path);
                            }
                            sendDirectory(context, path, sfile);
                            return;
                        } else {
                            if (this.cache.enabled()) {
                                CacheEntry now = this.cache.put(path, fprops);
                                if (now.shouldUseCached(context.request())) {
                                    context.response().setStatusCode(HttpResponseStatus.NOT_MODIFIED.code()).end();
                                    return;
                                }
                            }
                            sendFile(context, sfile, fprops);
                            return;
                        }
                    }
                    context.fail(res.cause());
                });
            }
        });
    }

    private void sendDirectory(RoutingContext context, String path, String file) {
        String indexPath;
        if (this.directoryListing) {
            sendDirectoryListing(file, context);
            return;
        }
        if (this.indexPage != null) {
            if (path.endsWith("/") && this.indexPage.startsWith("/")) {
                indexPath = path + this.indexPage.substring(1);
            } else if (!path.endsWith("/") && !this.indexPage.startsWith("/")) {
                indexPath = path + "/" + this.indexPage.substring(1);
            } else {
                indexPath = path + this.indexPage;
            }
            sendStatic(context, indexPath);
            return;
        }
        context.fail(HttpResponseStatus.FORBIDDEN.code());
    }

    private <T> T wrapInTCCLSwitch(Callable<T> callable) {
        try {
            if (this.classLoader == null) {
                return callable.call();
            }
            ClassLoader original = Thread.currentThread().getContextClassLoader();
            try {
                Thread.currentThread().setContextClassLoader(this.classLoader);
                T tCall = callable.call();
                Thread.currentThread().setContextClassLoader(original);
                return tCall;
            } catch (Throwable th) {
                Thread.currentThread().setContextClassLoader(original);
                throw th;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void isFileExisting(RoutingContext context, String file, Handler<AsyncResult<Boolean>> resultHandler) {
        FileSystem fs = context.vertx().fileSystem();
        wrapInTCCLSwitch(() -> {
            return fs.exists(file, resultHandler);
        });
    }

    private void getFileProps(RoutingContext context, String file, Handler<AsyncResult<FileProps>> resultHandler) {
        FileSystem fs = context.vertx().fileSystem();
        if (this.tune.useAsyncFS()) {
            wrapInTCCLSwitch(() -> {
                return fs.props(file, resultHandler);
            });
            return;
        }
        try {
            boolean tuneEnabled = this.tune.enabled();
            long start = tuneEnabled ? System.nanoTime() : 0L;
            FileProps props = (FileProps) wrapInTCCLSwitch(() -> {
                return fs.propsBlocking(file);
            });
            if (tuneEnabled) {
                this.tune.update(start, System.nanoTime());
            }
            resultHandler.handle(Future.succeededFuture(props));
        } catch (RuntimeException e) {
            resultHandler.handle(Future.failedFuture(e.getCause()));
        }
    }

    private void sendFile(RoutingContext context, String file, FileProps fileProps) {
        HttpServerRequest request = context.request();
        Long offset = null;
        long end = 0;
        MultiMap headers = null;
        if (this.rangeSupport) {
            String range = request.getHeader("Range");
            end = fileProps.size() - 1;
            if (range != null) {
                Matcher m = RANGE.matcher(range);
                if (m.matches()) {
                    try {
                        offset = Long.valueOf(Long.parseLong(m.group(1)));
                        if (offset.longValue() < 0 || offset.longValue() >= fileProps.size()) {
                            throw new IndexOutOfBoundsException();
                        }
                        String part = m.group(2);
                        if (part != null && part.length() > 0) {
                            end = Math.min(end, Long.parseLong(part));
                            if (end < offset.longValue()) {
                                throw new IndexOutOfBoundsException();
                            }
                        }
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        context.response().putHeader("Content-Range", "bytes */" + fileProps.size());
                        context.fail(HttpResponseStatus.REQUESTED_RANGE_NOT_SATISFIABLE.code());
                        return;
                    }
                }
            }
            headers = request.response().headers();
            headers.set("Accept-Ranges", "bytes");
            headers.set("Content-Length", Long.toString((end + 1) - (offset == null ? 0L : offset.longValue())));
        }
        writeCacheHeaders(request, fileProps);
        if (request.method() == HttpMethod.HEAD) {
            request.response().end();
            return;
        }
        if (this.rangeSupport && offset != null) {
            headers.set("Content-Range", "bytes " + offset + "-" + end + "/" + fileProps.size());
            request.response().setStatusCode(HttpResponseStatus.PARTIAL_CONTENT.code());
            long finalOffset = offset.longValue();
            long finalLength = (end + 1) - offset.longValue();
            wrapInTCCLSwitch(() -> {
                String contentType = MimeMapping.getMimeTypeForFilename(file);
                if (contentType != null) {
                    if (contentType.startsWith(NCXDocumentV2.NCXTags.text)) {
                        request.response().putHeader("Content-Type", contentType + ";charset=" + this.defaultContentEncoding);
                    } else {
                        request.response().putHeader("Content-Type", contentType);
                    }
                }
                return request.response().sendFile(file, finalOffset, finalLength, res2 -> {
                    if (res2.failed()) {
                        context.fail(res2.cause());
                    }
                });
            });
            return;
        }
        wrapInTCCLSwitch(() -> {
            String extension = getFileExtension(file);
            String contentType = MimeMapping.getMimeTypeForExtension(extension);
            if (this.compressedMediaTypes.contains(contentType) || this.compressedFileSuffixes.contains(extension)) {
                request.response().putHeader(HttpHeaders.CONTENT_ENCODING, HttpHeaders.IDENTITY);
            }
            if (contentType != null) {
                if (contentType.startsWith(NCXDocumentV2.NCXTags.text)) {
                    request.response().putHeader("Content-Type", contentType + ";charset=" + this.defaultContentEncoding);
                } else {
                    request.response().putHeader("Content-Type", contentType);
                }
            }
            if (request.version() == HttpVersion.HTTP_2 && this.http2PushMappings != null) {
                for (Http2PushMapping dependency : this.http2PushMappings) {
                    if (!dependency.isNoPush()) {
                        String dep = this.webRoot + "/" + dependency.getFilePath();
                        HttpServerResponse response = request.response();
                        getFileProps(context, dep, filePropsAsyncResult -> {
                            if (filePropsAsyncResult.succeeded()) {
                                writeCacheHeaders(request, (FileProps) filePropsAsyncResult.result());
                                response.push(HttpMethod.GET, "/" + dependency.getFilePath(), pushAsyncResult -> {
                                    if (pushAsyncResult.succeeded()) {
                                        HttpServerResponse res = (HttpServerResponse) pushAsyncResult.result();
                                        String depContentType = MimeMapping.getMimeTypeForExtension(file);
                                        if (depContentType != null) {
                                            if (depContentType.startsWith(NCXDocumentV2.NCXTags.text)) {
                                                res.putHeader("Content-Type", contentType + ";charset=" + this.defaultContentEncoding);
                                            } else {
                                                res.putHeader("Content-Type", contentType);
                                            }
                                        }
                                        res.sendFile(this.webRoot + "/" + dependency.getFilePath());
                                    }
                                });
                            }
                        });
                    }
                }
            } else if (this.http2PushMappings != null) {
                HttpServerResponse response2 = request.response();
                List<String> links = new ArrayList<>();
                for (Http2PushMapping dependency2 : this.http2PushMappings) {
                    String dep2 = this.webRoot + "/" + dependency2.getFilePath();
                    getFileProps(context, dep2, filePropsAsyncResult2 -> {
                        if (filePropsAsyncResult2.succeeded()) {
                            writeCacheHeaders(request, (FileProps) filePropsAsyncResult2.result());
                            links.add("<" + dependency2.getFilePath() + ">; rel=preload; as=" + dependency2.getExtensionTarget() + (dependency2.isNoPush() ? "; nopush" : ""));
                        }
                    });
                }
                response2.putHeader("Link", (Iterable<String>) links);
            }
            return request.response().sendFile(file, res2 -> {
                if (res2.failed()) {
                    context.fail(res2.cause());
                }
            });
        });
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setAllowRootFileSystemAccess(boolean allowRootFileSystemAccess) {
        this.allowRootFileSystemAccess = allowRootFileSystemAccess;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setWebRoot(String webRoot) {
        setRoot(webRoot);
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setFilesReadOnly(boolean readOnly) {
        this.filesReadOnly = readOnly;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setMaxAgeSeconds(long maxAgeSeconds) {
        if (maxAgeSeconds < 0) {
            throw new IllegalArgumentException("timeout must be >= 0");
        }
        this.maxAgeSeconds = maxAgeSeconds;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setMaxCacheSize(int maxCacheSize) {
        this.cache.setMaxSize(maxCacheSize);
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setCachingEnabled(boolean enabled) {
        this.cache.setEnabled(enabled);
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setDirectoryListing(boolean directoryListing) {
        this.directoryListing = directoryListing;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setDirectoryTemplate(String directoryTemplate) {
        this.directoryTemplateResource = directoryTemplate;
        this.directoryTemplate = null;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setEnableRangeSupport(boolean enableRangeSupport) {
        this.rangeSupport = enableRangeSupport;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setIncludeHidden(boolean includeHidden) {
        this.includeHidden = includeHidden;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setCacheEntryTimeout(long timeout) {
        this.cache.setCacheEntryTimeout(timeout);
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setIndexPage(String indexPage) {
        Objects.requireNonNull(indexPage);
        if (!indexPage.startsWith("/")) {
            indexPage = "/" + indexPage;
        }
        this.indexPage = indexPage;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setAlwaysAsyncFS(boolean alwaysAsyncFS) {
        this.tune.setAlwaysAsyncFS(alwaysAsyncFS);
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setHttp2PushMapping(List<Http2PushMapping> http2PushMap) {
        if (http2PushMap != null) {
            this.http2PushMappings = new ArrayList(http2PushMap);
        }
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler skipCompressionForMediaTypes(Set<String> mediaTypes) {
        if (mediaTypes != null) {
            this.compressedMediaTypes = new HashSet(mediaTypes);
        }
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler skipCompressionForSuffixes(Set<String> fileSuffixes) {
        if (fileSuffixes != null) {
            this.compressedFileSuffixes = new HashSet(fileSuffixes);
        }
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public synchronized StaticHandler setEnableFSTuning(boolean enableFSTuning) {
        this.tune.setEnabled(enableFSTuning);
        return this;
    }

    /* JADX WARN: Failed to check method for inline after forced processio.vertx.ext.web.handler.impl.StaticHandlerImpl.FSTune.access$102(io.vertx.ext.web.handler.impl.StaticHandlerImpl$FSTune, long):long */
    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setMaxAvgServeTimeNs(long maxAvgServeTimeNanoSeconds) {
        FSTune.access$102(this.tune, maxAvgServeTimeNanoSeconds);
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setSendVaryHeader(boolean sendVaryHeader) {
        this.sendVaryHeader = sendVaryHeader;
        return this;
    }

    @Override // io.vertx.ext.web.handler.StaticHandler
    public StaticHandler setDefaultContentEncoding(String contentEncoding) {
        this.defaultContentEncoding = contentEncoding;
        return this;
    }

    private String getFile(String path, RoutingContext context) {
        String file = this.webRoot + Utils.pathOffset(path, context);
        if (log.isTraceEnabled()) {
            log.trace("File to serve is " + file);
        }
        return file;
    }

    private void setRoot(String webRoot) {
        Objects.requireNonNull(webRoot);
        if (!this.allowRootFileSystemAccess) {
            for (File root : File.listRoots()) {
                if (webRoot.startsWith(root.getAbsolutePath())) {
                    throw new IllegalArgumentException("root cannot start with '" + root.getAbsolutePath() + OperatorName.SHOW_TEXT_LINE);
                }
            }
        }
        this.webRoot = webRoot;
    }

    private void sendDirectoryListing(String dir, RoutingContext context) {
        FileSystem fileSystem = context.vertx().fileSystem();
        HttpServerRequest request = context.request();
        fileSystem.readDir(dir, asyncResult -> {
            if (asyncResult.failed()) {
                context.fail(asyncResult.cause());
                return;
            }
            String accept = request.headers().get("accept");
            if (accept == null) {
                accept = "text/plain";
            }
            if (!accept.contains("html")) {
                if (accept.contains("json")) {
                    JsonArray json = new JsonArray();
                    for (String s : (List) asyncResult.result()) {
                        String file = s.substring(s.lastIndexOf(File.separatorChar) + 1);
                        if (this.includeHidden || file.charAt(0) != '.') {
                            json.add(file);
                        }
                    }
                    request.response().putHeader("content-type", "application/json");
                    request.response().end(json.encode());
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                for (String s2 : (List) asyncResult.result()) {
                    String file2 = s2.substring(s2.lastIndexOf(File.separatorChar) + 1);
                    if (this.includeHidden || file2.charAt(0) != '.') {
                        buffer.append(file2);
                        buffer.append('\n');
                    }
                }
                request.response().putHeader("content-type", "text/plain");
                request.response().end(buffer.toString());
                return;
            }
            String normalizedDir = context.normalisedPath();
            if (!normalizedDir.endsWith("/")) {
                normalizedDir = normalizedDir + "/";
            }
            StringBuilder files = new StringBuilder("<ul id=\"files\">");
            List<String> list = (List) asyncResult.result();
            Collections.sort(list);
            for (String s3 : list) {
                String file3 = s3.substring(s3.lastIndexOf(File.separatorChar) + 1);
                if (this.includeHidden || file3.charAt(0) != '.') {
                    files.append("<li><a href=\"");
                    files.append(normalizedDir);
                    files.append(file3);
                    files.append("\" title=\"");
                    files.append(file3);
                    files.append("\">");
                    files.append(file3);
                    files.append("</a></li>");
                }
            }
            files.append("</ul>");
            int slashPos = 0;
            int i = normalizedDir.length() - 2;
            while (true) {
                if (i <= 0) {
                    break;
                }
                if (normalizedDir.charAt(i) == '/') {
                    slashPos = i;
                    break;
                }
                i--;
            }
            String parent = "<a href=\"" + normalizedDir.substring(0, slashPos + 1) + "\">..</a>";
            request.response().putHeader("content-type", "text/html");
            request.response().end(directoryTemplate(context.vertx()).replace("{directory}", normalizedDir).replace("{parent}", parent).replace("{files}", files.toString()));
        });
    }

    private String getFileExtension(String file) {
        int li = file.lastIndexOf(46);
        if (li != -1 && li != file.length() - 1) {
            return file.substring(li + 1);
        }
        return null;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/StaticHandlerImpl$CacheEntry.class */
    private final class CacheEntry {
        final long createDate;
        final FileProps props;
        final long cacheEntryTimeout;

        private CacheEntry(FileProps props, long cacheEntryTimeout) {
            this.createDate = System.currentTimeMillis();
            this.props = props;
            this.cacheEntryTimeout = cacheEntryTimeout;
        }

        boolean shouldUseCached(HttpServerRequest request) {
            String ifModifiedSince = request.headers().get("if-modified-since");
            if (ifModifiedSince == null) {
                return false;
            }
            long ifModifiedSinceDate = Utils.parseRFC1123DateTime(ifModifiedSince);
            boolean modifiedSince = Utils.secondsFactor(this.props.lastModifiedTime()) > ifModifiedSinceDate;
            return !modifiedSince;
        }

        boolean isOutOfDate() {
            return System.currentTimeMillis() - this.createDate > this.cacheEntryTimeout;
        }

        public boolean isMissing() {
            return this.props == null;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/StaticHandlerImpl$FSTune.class */
    private static class FSTune {
        private static int NUM_SERVES_TUNING_FS_ACCESS = 1000;
        private volatile boolean enabled;
        private volatile boolean useAsyncFS;
        private long totalTime;
        private long numServesBlocking;
        private long nextAvgCheck;
        private long maxAvgServeTimeNanoSeconds;
        private boolean alwaysAsyncFS;

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        static /* synthetic */ long access$102(io.vertx.ext.web.handler.impl.StaticHandlerImpl.FSTune r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.maxAvgServeTimeNanoSeconds = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.vertx.ext.web.handler.impl.StaticHandlerImpl.FSTune.access$102(io.vertx.ext.web.handler.impl.StaticHandlerImpl$FSTune, long):long");
        }

        private FSTune() {
            this.enabled = true;
            this.nextAvgCheck = NUM_SERVES_TUNING_FS_ACCESS;
            this.maxAvgServeTimeNanoSeconds = StaticHandler.DEFAULT_MAX_AVG_SERVE_TIME_NS;
            this.alwaysAsyncFS = false;
        }

        static {
        }

        boolean enabled() {
            return this.enabled;
        }

        boolean useAsyncFS() {
            return this.alwaysAsyncFS || this.useAsyncFS;
        }

        synchronized void setEnabled(boolean enabled) {
            this.enabled = enabled;
            if (!enabled) {
                reset();
            }
        }

        void setAlwaysAsyncFS(boolean alwaysAsyncFS) {
            this.alwaysAsyncFS = alwaysAsyncFS;
        }

        synchronized void update(long start, long end) {
            long dur = end - start;
            this.totalTime += dur;
            this.numServesBlocking++;
            if (this.numServesBlocking == Long.MAX_VALUE) {
                reset();
                return;
            }
            if (this.numServesBlocking == this.nextAvgCheck) {
                double avg = this.totalTime / this.numServesBlocking;
                if (avg > this.maxAvgServeTimeNanoSeconds) {
                    this.useAsyncFS = true;
                    StaticHandlerImpl.log.info("Switching to async file system access in static file server as fs access is slow! (Average access time of " + avg + " ns)");
                    this.enabled = false;
                }
                this.nextAvgCheck += NUM_SERVES_TUNING_FS_ACCESS;
            }
        }

        synchronized void reset() {
            this.nextAvgCheck = NUM_SERVES_TUNING_FS_ACCESS;
            this.totalTime = 0L;
            this.numServesBlocking = 0L;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/StaticHandlerImpl$FSPropsCache.class */
    private class FSPropsCache {
        private Map<String, CacheEntry> propsCache;
        private long cacheEntryTimeout = 30000;
        private int maxCacheSize = 10000;

        FSPropsCache() {
            setEnabled(StaticHandler.DEFAULT_CACHING_ENABLED);
        }

        boolean enabled() {
            return this.propsCache != null;
        }

        synchronized void setMaxSize(int maxCacheSize) {
            if (maxCacheSize < 1) {
                throw new IllegalArgumentException("maxCacheSize must be >= 1");
            }
            if (this.maxCacheSize != maxCacheSize) {
                this.maxCacheSize = maxCacheSize;
                setEnabled(enabled(), true);
            }
        }

        void setEnabled(boolean enable) {
            setEnabled(enable, false);
        }

        private synchronized void setEnabled(boolean enable, boolean force) {
            if (force || enable != enabled()) {
                if (this.propsCache != null) {
                    this.propsCache.clear();
                }
                if (enable) {
                    this.propsCache = new LRUCache(this.maxCacheSize);
                } else {
                    this.propsCache = null;
                }
            }
        }

        void setCacheEntryTimeout(long timeout) {
            if (timeout < 1) {
                throw new IllegalArgumentException("timeout must be >= 1");
            }
            this.cacheEntryTimeout = timeout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void remove(String path) {
            if (this.propsCache != null) {
                this.propsCache.remove(path);
            }
        }

        CacheEntry get(String key) {
            if (this.propsCache != null) {
                return this.propsCache.get(key);
            }
            return null;
        }

        CacheEntry put(String path, FileProps props) {
            if (this.propsCache != null) {
                CacheEntry now = new CacheEntry(props, this.cacheEntryTimeout);
                this.propsCache.put(path, now);
                return now;
            }
            return null;
        }
    }
}
