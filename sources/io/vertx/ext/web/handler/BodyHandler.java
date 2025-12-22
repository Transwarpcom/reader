package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.BodyHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/BodyHandler.class */
public interface BodyHandler extends Handler<RoutingContext> {
    public static final long DEFAULT_BODY_LIMIT = -1;
    public static final String DEFAULT_UPLOADS_DIRECTORY = "file-uploads";
    public static final boolean DEFAULT_MERGE_FORM_ATTRIBUTES = true;
    public static final boolean DEFAULT_DELETE_UPLOADED_FILES_ON_END = false;
    public static final boolean DEFAULT_PREALLOCATE_BODY_BUFFER = false;

    @Fluent
    BodyHandler setHandleFileUploads(boolean z);

    @Fluent
    BodyHandler setBodyLimit(long j);

    @Fluent
    BodyHandler setUploadsDirectory(String str);

    @Fluent
    BodyHandler setMergeFormAttributes(boolean z);

    @Fluent
    BodyHandler setDeleteUploadedFilesOnEnd(boolean z);

    @Fluent
    BodyHandler setPreallocateBodyBuffer(boolean z);

    static BodyHandler create() {
        return new BodyHandlerImpl();
    }

    static BodyHandler create(boolean handleFileUploads) {
        return new BodyHandlerImpl(handleFileUploads);
    }

    static BodyHandler create(String uploadDirectory) {
        return new BodyHandlerImpl(uploadDirectory);
    }
}
