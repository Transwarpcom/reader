package io.vertx.ext.web.multipart.impl;

import io.vertx.ext.web.multipart.FormDataPart;
import io.vertx.ext.web.multipart.MultipartForm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/multipart/impl/MultipartFormImpl.class */
public class MultipartFormImpl implements MultipartForm {
    private final List<FormDataPart> parts = new ArrayList();

    @Override // io.vertx.ext.web.multipart.MultipartForm
    public MultipartForm attribute(String name, String value) {
        this.parts.add(new FormDataPartImpl(name, value));
        return this;
    }

    @Override // io.vertx.ext.web.multipart.MultipartForm
    public MultipartForm textFileUpload(String name, String filename, String pathname, String mediaType) {
        this.parts.add(new FormDataPartImpl(name, filename, pathname, mediaType, true));
        return this;
    }

    @Override // io.vertx.ext.web.multipart.MultipartForm
    public MultipartForm binaryFileUpload(String name, String filename, String pathname, String mediaType) {
        this.parts.add(new FormDataPartImpl(name, filename, pathname, mediaType, false));
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<FormDataPart> iterator() {
        return this.parts.iterator();
    }
}
