package io.vertx.ext.web.multipart.impl;

import io.vertx.ext.web.multipart.FormDataPart;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/multipart/impl/FormDataPartImpl.class */
public class FormDataPartImpl implements FormDataPart {
    private final String name;
    private final String value;
    private final String filename;
    private final String mediaType;
    private final String pathname;
    private final Boolean text;

    public FormDataPartImpl(String name, String value) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (value == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.value = value;
        this.filename = null;
        this.pathname = null;
        this.mediaType = null;
        this.text = null;
    }

    public FormDataPartImpl(String name, String filename, String pathname, String mediaType, boolean text) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (filename == null) {
            throw new NullPointerException();
        }
        if (pathname == null) {
            throw new NullPointerException();
        }
        if (mediaType == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.value = null;
        this.filename = filename;
        this.pathname = pathname;
        this.mediaType = mediaType;
        this.text = Boolean.valueOf(text);
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public String name() {
        return this.name;
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public boolean isAttribute() {
        return this.value != null;
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public boolean isFileUpload() {
        return this.value == null;
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public String value() {
        return this.value;
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public String filename() {
        return this.filename;
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public String pathname() {
        return this.pathname;
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public String mediaType() {
        return this.mediaType;
    }

    @Override // io.vertx.ext.web.multipart.FormDataPart
    public Boolean isText() {
        return this.text;
    }
}
