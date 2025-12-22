package io.vertx.ext.web.handler.impl;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.impl.FileUploadImpl;
import java.io.File;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/BodyHandlerImpl.class */
public class BodyHandlerImpl implements BodyHandler {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) BodyHandlerImpl.class);
    private static final String BODY_HANDLED = "__body-handled";
    private long bodyLimit;
    private boolean handleFileUploads;
    private String uploadsDir;
    private boolean mergeFormAttributes;
    private boolean deleteUploadedFilesOnEnd;
    private boolean isPreallocateBodyBuffer;
    private static final int DEFAULT_INITIAL_BODY_BUFFER_SIZE = 1024;

    public BodyHandlerImpl() {
        this(true, BodyHandler.DEFAULT_UPLOADS_DIRECTORY);
    }

    public BodyHandlerImpl(boolean handleFileUploads) {
        this(handleFileUploads, BodyHandler.DEFAULT_UPLOADS_DIRECTORY);
    }

    public BodyHandlerImpl(String uploadDirectory) {
        this(true, uploadDirectory);
    }

    private BodyHandlerImpl(boolean handleFileUploads, String uploadDirectory) {
        this.bodyLimit = -1L;
        this.mergeFormAttributes = true;
        this.deleteUploadedFilesOnEnd = false;
        this.isPreallocateBodyBuffer = false;
        this.handleFileUploads = handleFileUploads;
        setUploadsDirectory(uploadDirectory);
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        HttpServerRequest request = context.request();
        if (request.headers().contains(HttpHeaders.UPGRADE, HttpHeaders.WEBSOCKET, true)) {
            context.next();
            return;
        }
        Boolean handled = (Boolean) context.get(BODY_HANDLED);
        if (handled == null || !handled.booleanValue()) {
            long contentLength = this.isPreallocateBodyBuffer ? parseContentLengthHeader(request) : -1L;
            BHandler handler = new BHandler(context, contentLength);
            request.handler2((Handler<Buffer>) handler);
            request.endHandler(v -> {
                handler.end();
            });
            context.put(BODY_HANDLED, true);
            return;
        }
        if (this.mergeFormAttributes && request.isExpectMultipart()) {
            request.params().addAll(request.formAttributes());
        }
        context.next();
    }

    @Override // io.vertx.ext.web.handler.BodyHandler
    public BodyHandler setHandleFileUploads(boolean handleFileUploads) {
        this.handleFileUploads = handleFileUploads;
        return this;
    }

    @Override // io.vertx.ext.web.handler.BodyHandler
    public BodyHandler setBodyLimit(long bodyLimit) {
        this.bodyLimit = bodyLimit;
        return this;
    }

    @Override // io.vertx.ext.web.handler.BodyHandler
    public BodyHandler setUploadsDirectory(String uploadsDirectory) {
        this.uploadsDir = uploadsDirectory;
        return this;
    }

    @Override // io.vertx.ext.web.handler.BodyHandler
    public BodyHandler setMergeFormAttributes(boolean mergeFormAttributes) {
        this.mergeFormAttributes = mergeFormAttributes;
        return this;
    }

    @Override // io.vertx.ext.web.handler.BodyHandler
    public BodyHandler setDeleteUploadedFilesOnEnd(boolean deleteUploadedFilesOnEnd) {
        this.deleteUploadedFilesOnEnd = deleteUploadedFilesOnEnd;
        return this;
    }

    @Override // io.vertx.ext.web.handler.BodyHandler
    public BodyHandler setPreallocateBodyBuffer(boolean isPreallocateBodyBuffer) {
        this.isPreallocateBodyBuffer = isPreallocateBodyBuffer;
        return this;
    }

    private long parseContentLengthHeader(HttpServerRequest request) throws NumberFormatException {
        String contentLength = request.getHeader(HttpHeaders.CONTENT_LENGTH);
        if (contentLength == null || contentLength.isEmpty()) {
            return -1L;
        }
        try {
            long parsedContentLength = Long.parseLong(contentLength);
            if (parsedContentLength < 0) {
                return -1L;
            }
            return parsedContentLength;
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/BodyHandlerImpl$BHandler.class */
    private class BHandler implements Handler<Buffer> {
        private static final int MAX_PREALLOCATED_BODY_BUFFER_BYTES = 65535;
        RoutingContext context;
        Buffer body;
        boolean failed;
        boolean ended;
        final boolean isMultipart;
        final boolean isUrlEncoded;
        AtomicInteger uploadCount = new AtomicInteger();
        AtomicBoolean cleanup = new AtomicBoolean(false);
        long uploadSize = 0;

        public BHandler(RoutingContext context, long contentLength) {
            this.context = context;
            Set<FileUpload> fileUploads = context.fileUploads();
            String contentType = context.request().getHeader(HttpHeaders.CONTENT_TYPE);
            if (contentType == null) {
                this.isMultipart = false;
                this.isUrlEncoded = false;
            } else {
                String lowerCaseContentType = contentType.toLowerCase();
                this.isMultipart = lowerCaseContentType.startsWith(HttpHeaderValues.MULTIPART_FORM_DATA.toString());
                this.isUrlEncoded = lowerCaseContentType.startsWith(HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString());
            }
            initBodyBuffer(contentLength);
            if (this.isMultipart || this.isUrlEncoded) {
                context.request().setExpectMultipart(true);
                if (BodyHandlerImpl.this.handleFileUploads) {
                    makeUploadDir(context.vertx().fileSystem());
                }
                context.request().uploadHandler(upload -> {
                    if (BodyHandlerImpl.this.bodyLimit != -1 && upload.isSizeAvailable()) {
                        long size = this.uploadSize + upload.size();
                        if (size > BodyHandlerImpl.this.bodyLimit) {
                            this.failed = true;
                            context.fail(413);
                            return;
                        }
                    }
                    if (BodyHandlerImpl.this.handleFileUploads) {
                        this.uploadCount.incrementAndGet();
                        String uploadedFileName = new File(BodyHandlerImpl.this.uploadsDir, UUID.randomUUID().toString()).getPath();
                        upload.streamToFileSystem(uploadedFileName);
                        FileUploadImpl fileUpload = new FileUploadImpl(uploadedFileName, upload);
                        fileUploads.add(fileUpload);
                        upload.exceptionHandler(t -> {
                            deleteFileUploads();
                            context.fail(t);
                        });
                        upload.endHandler(v -> {
                            uploadEnded();
                        });
                    }
                });
            }
            context.request().exceptionHandler(t -> {
                deleteFileUploads();
                context.fail(t);
            });
        }

        private void initBodyBuffer(long contentLength) {
            int initialBodyBufferSize;
            if (contentLength < 0) {
                initialBodyBufferSize = 1024;
            } else if (contentLength > WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                initialBodyBufferSize = 65535;
            } else {
                initialBodyBufferSize = (int) contentLength;
            }
            if (BodyHandlerImpl.this.bodyLimit != -1) {
                initialBodyBufferSize = (int) Math.min(initialBodyBufferSize, BodyHandlerImpl.this.bodyLimit);
            }
            this.body = Buffer.buffer(initialBodyBufferSize);
        }

        private void makeUploadDir(FileSystem fileSystem) {
            if (!fileSystem.existsBlocking(BodyHandlerImpl.this.uploadsDir)) {
                fileSystem.mkdirsBlocking(BodyHandlerImpl.this.uploadsDir);
            }
        }

        @Override // io.vertx.core.Handler
        public void handle(Buffer buff) {
            if (this.failed) {
                return;
            }
            this.uploadSize += buff.length();
            if (BodyHandlerImpl.this.bodyLimit != -1 && this.uploadSize > BodyHandlerImpl.this.bodyLimit) {
                this.failed = true;
                this.context.fail(413);
                this.context.vertx().runOnContext(v -> {
                    deleteFileUploads();
                });
            } else if (!this.isMultipart) {
                this.body.appendBuffer(buff);
            }
        }

        void uploadEnded() {
            int count = this.uploadCount.decrementAndGet();
            if (this.ended && count == 0) {
                doEnd();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void end() {
            this.ended = true;
            if (this.uploadCount.get() == 0) {
                doEnd();
            }
        }

        void doEnd() {
            if (!this.failed) {
                if (BodyHandlerImpl.this.deleteUploadedFilesOnEnd) {
                    this.context.addBodyEndHandler(x -> {
                        deleteFileUploads();
                    });
                }
                HttpServerRequest req = this.context.request();
                if (BodyHandlerImpl.this.mergeFormAttributes && req.isExpectMultipart()) {
                    req.params().addAll(req.formAttributes());
                }
                this.context.setBody(this.body);
                this.context.next();
                return;
            }
            deleteFileUploads();
        }

        private void deleteFileUploads() {
            if (this.cleanup.compareAndSet(false, true) && BodyHandlerImpl.this.handleFileUploads) {
                for (FileUpload fileUpload : this.context.fileUploads()) {
                    FileSystem fileSystem = this.context.vertx().fileSystem();
                    String uploadedFileName = fileUpload.uploadedFileName();
                    fileSystem.exists(uploadedFileName, existResult -> {
                        if (existResult.failed()) {
                            BodyHandlerImpl.log.warn("Could not detect if uploaded file exists, not deleting: " + uploadedFileName, existResult.cause());
                        } else if (((Boolean) existResult.result()).booleanValue()) {
                            fileSystem.delete(uploadedFileName, deleteResult -> {
                                if (deleteResult.failed()) {
                                    BodyHandlerImpl.log.warn("Delete of uploaded file failed: " + uploadedFileName, deleteResult.cause());
                                }
                            });
                        }
                    });
                }
            }
        }
    }
}
