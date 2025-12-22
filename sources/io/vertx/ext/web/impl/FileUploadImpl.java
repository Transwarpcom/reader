package io.vertx.ext.web.impl;

import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.ext.web.FileUpload;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/FileUploadImpl.class */
public class FileUploadImpl implements FileUpload {
    private final String uploadedFileName;
    private final HttpServerFileUpload upload;

    public FileUploadImpl(String uploadedFileName, HttpServerFileUpload upload) {
        this.uploadedFileName = uploadedFileName;
        this.upload = upload;
    }

    @Override // io.vertx.ext.web.FileUpload
    public String name() {
        return this.upload.name();
    }

    @Override // io.vertx.ext.web.FileUpload
    public String uploadedFileName() {
        return this.uploadedFileName;
    }

    @Override // io.vertx.ext.web.FileUpload
    public String fileName() {
        return this.upload.filename();
    }

    @Override // io.vertx.ext.web.FileUpload
    public long size() {
        return this.upload.size();
    }

    @Override // io.vertx.ext.web.FileUpload
    public String contentType() {
        return this.upload.contentType();
    }

    @Override // io.vertx.ext.web.FileUpload
    public String contentTransferEncoding() {
        return this.upload.contentTransferEncoding();
    }

    @Override // io.vertx.ext.web.FileUpload
    public String charSet() {
        return this.upload.charset();
    }
}
