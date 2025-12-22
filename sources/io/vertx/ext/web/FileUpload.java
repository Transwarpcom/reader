package io.vertx.ext.web;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/FileUpload.class */
public interface FileUpload {
    String name();

    String uploadedFileName();

    String fileName();

    long size();

    String contentType();

    String contentTransferEncoding();

    String charSet();
}
